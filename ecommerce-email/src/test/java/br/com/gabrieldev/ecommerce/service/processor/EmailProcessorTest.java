package br.com.gabrieldev.ecommerce.service.processor;

import br.com.gabrieldev.ecommerce.domain.Email;
import br.com.gabrieldev.ecommerce.service.EmailService;
import br.com.gabrieldev.ecommerce.util.constants.Constants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class tests every {@link EmailProcessor}'s method
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class EmailProcessorTest {

    @InjectMocks
    private EmailProcessor emailProcessor;

    private ObjectMapper objectMapper;

    @Mock
    private EmailService emailService;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);

        this.objectMapper = generateObjectMapper();

        ReflectionTestUtils.setField(emailProcessor, "objectMapper", objectMapper);
    }

    private ObjectMapper generateObjectMapper(){
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.findAndRegisterModules();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Test
    void process_whenPassedJsonDoesntFitToEmailClasse_thenThrowResponseStatusException(){
        doNothing().when(emailService).sendEmail(any(Email.class));

        Assertions.assertThrows(
                ResponseStatusException.class,
                () -> emailProcessor.process(generateExchange(Constants.TEST))
        );
    }

    @Test
    void process_whenPassedJsonFitToEmailClasse_thenEmailIsSent(){
        doNothing().when(emailService).sendEmail(any(Email.class));

        var emailJson = "{\"reciever\":\"test@email.com\", \"subject\":\"test\", \"body\":\"test\"}";

        Assertions.assertDoesNotThrow(() -> emailProcessor.process(generateExchange(emailJson)));
    }

    private Exchange generateExchange(String body){
        var mockedMessage = mock(Message.class);
        when(mockedMessage.getHeader(anyString())).thenReturn(Constants.TEST);
        when(mockedMessage.getBody()).thenReturn(body);

        var mockedExchange = mock(Exchange.class);
        when(mockedExchange.getIn()).thenReturn(mockedMessage);
        when(mockedExchange.getMessage()).thenReturn(mockedMessage);

        return mockedExchange;
    }
}

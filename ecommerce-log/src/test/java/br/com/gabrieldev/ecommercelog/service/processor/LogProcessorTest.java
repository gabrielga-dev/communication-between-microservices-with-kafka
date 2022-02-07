package br.com.gabrieldev.ecommercelog.service.processor;

import br.com.gabrieldev.ecommercelog.domain.dto.LogDTO;
import br.com.gabrieldev.ecommercelog.service.LogService;
import br.com.gabrieldev.ecommercelog.util.constants.Constants;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

/**
 * This class tests every mehtod at {@link LogProcessor}
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class LogProcessorTest {

    @InjectMocks
    private LogProcessor logProcessor;

    @Mock
    private LogService logService;

    @BeforeEach
    private void beforeEach() throws IOException {
        MockitoAnnotations.initMocks(this);

        when(logService.save(anyString(), anyString())).thenReturn(new LogDTO());
    }

    @Test
    void process_whenExchangeIsPassed_thenNoExceptionIsThrown(){
        var mockedMessage = mock(Message.class);
        when(mockedMessage.getHeader(anyString())).thenReturn(Constants.TEST);
        when(mockedMessage.getBody()).thenReturn(Constants.TEST);

        var mockedExchange = mock(Exchange.class);
        when(mockedExchange.getIn()).thenReturn(mockedMessage);
        when(mockedExchange.getMessage()).thenReturn(mockedMessage);

        Assertions.assertDoesNotThrow(() -> logProcessor.process(mockedExchange));
    }

}

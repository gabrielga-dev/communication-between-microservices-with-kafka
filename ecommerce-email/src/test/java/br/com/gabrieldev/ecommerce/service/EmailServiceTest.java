package br.com.gabrieldev.ecommerce.service;

import br.com.gabrieldev.ecommerce.domain.Email;
import br.com.gabrieldev.ecommerce.domain.mapper.EmailMapper;
import br.com.gabrieldev.ecommerce.util.constants.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

/**
 * This class tests every {@link EmailService}'s method
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender mailSender;

    private EmailMapper emailMapper;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);

        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        this.emailMapper = new EmailMapper();

        ReflectionTestUtils.setField(emailMapper, "mailUsername", Constants.TEST);
        ReflectionTestUtils.setField(emailService, "emailMapper", emailMapper);

    }

    @Test
    void sendEmail_whenValuesArePassed_thenEmailIsSent(){
        var emailToBeSent = new Email(
                "reciever@email.com",
                Constants.TEST,
                Constants.TEST
        );
        Assertions.assertDoesNotThrow(() -> emailService.sendEmail(emailToBeSent));
    }
}

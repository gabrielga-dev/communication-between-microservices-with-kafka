package br.com.gabrieldev.ecommerce.domain.mapper;

import br.com.gabrieldev.ecommerce.domain.Email;
import br.com.gabrieldev.ecommerce.util.constants.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * This class tests every {@link EmailMapper}'s method
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class EmailMapperTest {

    @Test
    void toSimpleMailMessage_whenCorrectValuesArePassed_thenReturnsSimpleMailMessage(){
        var originEmailAddress = "test@email.com";

        var mapper = new EmailMapper();
        ReflectionTestUtils.setField(mapper, "mailUsername", originEmailAddress);

        var emailToBeParsed = new Email(
                "destiny@gmail.com",
                Constants.TEST,
                Constants.TEST
        );

        var parsedEmail = mapper.toSimpleMailMessage(emailToBeParsed);

        Assertions.assertEquals(parsedEmail.getFrom(), originEmailAddress);
        Assertions.assertEquals(parsedEmail.getSubject(), emailToBeParsed.getSubject());
        Assertions.assertEquals(parsedEmail.getText(), emailToBeParsed.getBody());
    }
}

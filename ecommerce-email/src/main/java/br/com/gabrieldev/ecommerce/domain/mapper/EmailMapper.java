package br.com.gabrieldev.ecommerce.domain.mapper;

import br.com.gabrieldev.ecommerce.domain.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * This class holds every mapping method for {@link Email} class
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Component
public class EmailMapper {

    @Value("${mail.username}")
    private String mailUsername;

    /**
     * This method receive a {@link Email} object and it's map to a {@link SimpleMailMessage} object
     *
     * @param email {@link Email} object that holds the email data
     * @return {@link SimpleMailMessage} object to be used to send the email
     * */
    public SimpleMailMessage toSimpleMailMessage(Email email){
        var message = new SimpleMailMessage();
        message.setFrom(mailUsername);
        message.setTo(email.getReciever());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        return message;
    }
}

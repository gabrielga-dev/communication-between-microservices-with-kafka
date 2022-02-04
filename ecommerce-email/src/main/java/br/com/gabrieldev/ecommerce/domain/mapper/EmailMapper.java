package br.com.gabrieldev.ecommerce.domain.mapper;

import br.com.gabrieldev.ecommerce.domain.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    @Value("${mail.username}")
    private String mailUsername;

    public SimpleMailMessage toSimpleMailMessage(Email email){
        var message = new SimpleMailMessage();
        message.setFrom(mailUsername);
        message.setTo(email.getReciever());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());
        return message;
    }
}

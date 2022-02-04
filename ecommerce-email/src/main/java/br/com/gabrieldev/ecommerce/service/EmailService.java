package br.com.gabrieldev.ecommerce.service;

import br.com.gabrieldev.ecommerce.domain.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    public void sendEmail(Email email){
        log.info(email.toString());
    }
}

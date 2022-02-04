package br.com.gabrieldev.ecommerce.service;

import br.com.gabrieldev.ecommerce.domain.Email;
import br.com.gabrieldev.ecommerce.domain.mapper.EmailMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailMapper emailMapper;
    private final JavaMailSender emailSender;

    public void sendEmail(Email email){
        emailSender.send(emailMapper.toSimpleMailMessage(email));
    }
}

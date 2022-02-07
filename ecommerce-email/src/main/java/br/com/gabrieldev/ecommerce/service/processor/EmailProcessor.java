package br.com.gabrieldev.ecommerce.service.processor;

import br.com.gabrieldev.ecommerce.domain.Email;
import br.com.gabrieldev.ecommerce.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

/**
 * This class holds the process method for every message from email topic
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Slf4j
@Component
@RequiredArgsConstructor
public class EmailProcessor implements Processor {

    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    @Override
    public void process(Exchange exchange) {
        log.info("Email message recieved: {}", exchange.getMessage().getBody());
        try {
            emailService.sendEmail(
                objectMapper.readValue((String) exchange.getMessage().getBody(), Email.class)
            );
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Can't parse the object into json",
                    e
            );
        }
    }
}

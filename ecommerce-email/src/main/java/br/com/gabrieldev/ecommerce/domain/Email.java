package br.com.gabrieldev.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents the message structure of a body of a email topic message
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Email {
    private String reciever;
    private String subject, body;
}

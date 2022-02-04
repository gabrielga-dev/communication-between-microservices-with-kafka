package br.com.gabrieldev.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Email {
    private String sender, reciever;
    private String subject, body;
}

package br.com.gabriel.dev.ecommerceorder.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class that represents emails
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class EmailDTO {
    private String reciever;
    private String subject, body;
}

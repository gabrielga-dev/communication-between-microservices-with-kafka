package br.com.gabriel.dev.ecommerceorder.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Class that represents incomming orders
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class OrderDTO {
    private String userId;
    private String userEmailAddress;
    private BigDecimal amount;
}

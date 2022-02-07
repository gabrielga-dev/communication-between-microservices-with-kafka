package br.com.gabriel.dev.ecommerceorder.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class OrderDTO {
    private String userId;
    private String userEmailAddress;
    private BigDecimal amount;
}

package br.com.gabrieldev.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Order {
    private String userId, orderId;
    private BigDecimal amount;
}

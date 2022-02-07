package br.com.gabriel.dev.ecommerceorder.domain.mapper;

import br.com.gabriel.dev.ecommerceorder.domain.dto.EmailDTO;
import br.com.gabriel.dev.ecommerceorder.domain.dto.OrderDTO;
import br.com.gabriel.dev.ecommerceorder.domain.entity.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderMapper {

    public Order toDomain(OrderDTO orderDTO){
        return new Order(
                null,
                orderDTO.getUserId(),
                orderDTO.getUserEmailAddress(),
                orderDTO.getAmount(),
                LocalDateTime.now()
        );
    }
}

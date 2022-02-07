package br.com.gabriel.dev.ecommerceorder.domain.mapper;

import br.com.gabriel.dev.ecommerceorder.domain.dto.EmailDTO;
import br.com.gabriel.dev.ecommerceorder.domain.dto.OrderDTO;
import br.com.gabriel.dev.ecommerceorder.domain.entity.Order;
import br.com.gabriel.dev.ecommerceorder.util.constants.EmailConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * This class has methods for mapping an {@link Order} ou an {@link OrderDTO} into another java class
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Component
public class OrderMapper {

    /**
     * This method map an {@link OrderDTO} object into a {@link Order}
     * @param orderDTO {@link OrderDTO} java object that contains the data that is about to be saved on our database
     * @return {@link Order} java object that is used to save information on our database
     */
    public Order toDomain(OrderDTO orderDTO){
        return new Order(
                null,
                orderDTO.getUserId(),
                orderDTO.getUserEmailAddress(),
                orderDTO.getAmount(),
                LocalDateTime.now()
        );
    }

    /**
     * This method map an {@link OrderDTO} object into a {@link EmailDTO}
     * @param orderDTO {@link OrderDTO} java object that contains the data that is needed for building the email
     * structure
     * @return {@link EmailDTO} java object that is used for sending kafka messages into email topic
     */
    public EmailDTO toEmail(OrderDTO orderDTO) {
        return new EmailDTO(
                orderDTO.getUserEmailAddress(),
                EmailConstants.EMAIL_SUBJECT,
                EmailConstants.EMAIL_DEFAULT_BODY
        );
    }
}

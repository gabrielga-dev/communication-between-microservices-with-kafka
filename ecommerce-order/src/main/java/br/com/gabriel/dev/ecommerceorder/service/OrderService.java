package br.com.gabriel.dev.ecommerceorder.service;

import br.com.gabriel.dev.ecommerceorder.domain.dto.EmailDTO;
import br.com.gabriel.dev.ecommerceorder.domain.dto.OrderDTO;
import br.com.gabriel.dev.ecommerceorder.domain.mapper.OrderMapper;
import br.com.gabriel.dev.ecommerceorder.repository.OrderRepository;
import br.com.gabriel.dev.ecommerceorder.service.dispatcher.KafkaDispatcher;
import br.com.gabriel.dev.ecommerceorder.util.constants.KafkaConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * This class holds the methods for order dealing
 *
 * @author Gabriel Guimarães de Almeida
 * */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final KafkaDispatcher<OrderDTO> kafkaDispatcherOrder;
    private final KafkaDispatcher<EmailDTO> kafkaDispatcherEmail;

    /**
     * This method save the incomming order on our database and send 2 messages: one to inform the new created order
     * and other for email dispatch
     *
     * @param orderDTO {@link OrderDTO} java object that contains the new order's data
     * @return {@link OrderDTO} java object that contains the new order's data
     * */
    public OrderDTO saveAndDispatchMessages(OrderDTO orderDTO) {
        orderRepository.save(
                orderMapper.toDomain(orderDTO)
        );

        sendMessages(orderDTO);

        return orderDTO;
    }

    private void sendMessages(OrderDTO orderDTO) {
        var emailToBeSent = orderMapper.toEmail(orderDTO);

        kafkaDispatcherOrder.send(
                KafkaConstants.TOPIC_NEW_ORDER, orderDTO.getUserId(), orderDTO,
                (x, y) -> log.info("Order message dispatched!")
        );
        kafkaDispatcherEmail.send(
                KafkaConstants.TOPIC_SEND_EMAIL, orderDTO.getUserId(), emailToBeSent,
                (x, y) -> log.info("Email message dispatched!")
        );
    }
}

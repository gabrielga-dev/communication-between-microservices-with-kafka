package br.com.gabriel.dev.ecommerceorder.service;

import br.com.gabriel.dev.ecommerceorder.domain.dto.EmailDTO;
import br.com.gabriel.dev.ecommerceorder.domain.dto.OrderDTO;
import br.com.gabriel.dev.ecommerceorder.domain.mapper.OrderMapper;
import br.com.gabriel.dev.ecommerceorder.repository.OrderRepository;
import br.com.gabriel.dev.ecommerceorder.service.dispatcher.KafkaDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final KafkaDispatcher<OrderDTO> kafkaDispatcherOrder;
    private final KafkaDispatcher<EmailDTO> kafkaDispatcherEmail;

    public OrderDTO save(OrderDTO orderDTO) {
        orderRepository.save(
                orderMapper.toDomain(orderDTO)
        );

        sendMessages(orderDTO);

        return orderDTO;
    }

    private void sendMessages(OrderDTO orderDTO) {

    }
}

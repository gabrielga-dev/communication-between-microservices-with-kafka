package br.com.gabriel.dev.ecommerceorder.service;

import br.com.gabriel.dev.ecommerceorder.domain.dto.EmailDTO;
import br.com.gabriel.dev.ecommerceorder.domain.dto.OrderDTO;
import br.com.gabriel.dev.ecommerceorder.domain.entity.Order;
import br.com.gabriel.dev.ecommerceorder.domain.mapper.OrderMapper;
import br.com.gabriel.dev.ecommerceorder.repository.OrderRepository;
import br.com.gabriel.dev.ecommerceorder.service.dispatcher.KafkaDispatcher;
import br.com.gabriel.dev.ecommerceorder.util.constants.Constants;
import org.apache.kafka.clients.producer.Callback;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * This class tests every mehtod at {@link OrderService}
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private KafkaDispatcher<OrderDTO> kafkaDispatcherOrder;

    @Mock
    private KafkaDispatcher<EmailDTO> kafkaDispatcherEmail;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);

        when(orderMapper.toDomain(any(OrderDTO.class))).thenCallRealMethod();
        when(orderMapper.toEmail(any(OrderDTO.class))).thenCallRealMethod();

        when(orderRepository.save(any(Order.class))).thenReturn(new Order());

        doNothing().when(kafkaDispatcherOrder).send(anyString(), anyString(), any(OrderDTO.class), any(Callback.class));
        doNothing().when(kafkaDispatcherEmail).send(anyString(), anyString(), any(EmailDTO.class), any(Callback.class));
    }

    @Test
    void saveAndDispatchMessages_whenPassedCorrectValues_thenSendMessagesCorrectlyAndReturnCorrectValue(){
        var orderToSave = new OrderDTO(
                Constants.TEST,
                Constants.TEST,
                BigDecimal.TEN
        );

        var savedValue = orderService.saveAndDispatchMessages(orderToSave);

        Assertions.assertNotNull(savedValue);
        Assertions.assertEquals(orderToSave.getUserEmailAddress(), savedValue.getUserEmailAddress());
        Assertions.assertEquals(orderToSave.getUserId(), savedValue.getUserId());
        Assertions.assertEquals(orderToSave.getAmount(), savedValue.getAmount());
    }
}

package br.com.gabriel.dev.ecommerceorder.controller;

import br.com.gabriel.dev.ecommerceorder.BaseIntegration;
import br.com.gabriel.dev.ecommerceorder.domain.dto.OrderDTO;
import br.com.gabriel.dev.ecommerceorder.service.OrderService;
import br.com.gabriel.dev.ecommerceorder.util.constants.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This class tests every endpoint at {@link OrderController}
 *
 * @author Gabriel Guimarães de Almeida
 * */
public class OrderControllerIT extends BaseIntegration {

    private static final String URL = "/api/order";

    @MockBean
    private OrderService orderService;

    private OrderDTO expectedOrder;

    @BeforeEach
    public void setup(){
        this.expectedOrder = new OrderDTO(
                Constants.TEST,
                Constants.TEST,
                BigDecimal.TEN
        );

        when(orderService.saveAndDispatchMessages(any(OrderDTO.class))).thenReturn(expectedOrder);
    }

    @Test
    void saveNewOrder_whenCalled_thenSavedValueIsReturned() throws Exception {
        var jsonResponse = mockMvc.perform(
                    post(URL).contentType(MediaType.APPLICATION_JSON)
                            .content(objectConverterUtil.toJson(expectedOrder))
                ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        var returnedOrder = objectConverterUtil
                .getObjectMapper().readValue(jsonResponse, OrderDTO.class);

        Assertions.assertNotNull(returnedOrder);
        Assertions.assertEquals(returnedOrder.getAmount(), expectedOrder.getAmount());
        Assertions.assertEquals(returnedOrder.getUserEmailAddress(), expectedOrder.getUserEmailAddress());
        Assertions.assertEquals(returnedOrder.getUserId(), expectedOrder.getUserId());
    }
}

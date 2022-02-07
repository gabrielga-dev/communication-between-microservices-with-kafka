package br.com.gabriel.dev.ecommerceorder.controller;

import br.com.gabriel.dev.ecommerceorder.domain.dto.OrderDTO;
import br.com.gabriel.dev.ecommerceorder.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class contains all endpoints for dealing with the orders
 *
 * @author Gabriel Guimarães de Almeida
 * */
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    /**
     * POST: api/order
     *
     * This endpoint recieve a new order, saves it on our database and send 2 kafka messages:one to inform the new created order
     * and other for email dispatch
     * @param orderDTO {@link OrderDTO} java object that contains the new order's data
     * @return {@link ResponseEntity}<{@link OrderDTO}> a response entity that holds a  java object that contains the
     * new order's data
     * */
    @PostMapping
    public ResponseEntity<OrderDTO> saveNewOrder(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.saveAndDispatchMessages(orderDTO));
    }
}

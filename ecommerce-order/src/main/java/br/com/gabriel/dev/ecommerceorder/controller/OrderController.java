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

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> filterLog(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.save(orderDTO));
    }
}

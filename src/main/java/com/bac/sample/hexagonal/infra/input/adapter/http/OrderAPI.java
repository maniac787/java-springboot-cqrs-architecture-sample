package com.bac.sample.hexagonal.infra.input.adapter.http;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bac.sample.hexagonal.domain.Orders;
import com.bac.sample.hexagonal.infra.input.port.OrderInputPort;

@RestController
@RequestMapping(value = "order")
public class OrderAPI {
    
    @Autowired
    OrderInputPort orderInputPort;

    @PostMapping(value = "create", produces=MediaType.APPLICATION_JSON_VALUE)
    public Orders create( @RequestParam String customerId, @RequestParam BigDecimal total ) {
        return orderInputPort.createOrder(customerId, total);
        }
    
}

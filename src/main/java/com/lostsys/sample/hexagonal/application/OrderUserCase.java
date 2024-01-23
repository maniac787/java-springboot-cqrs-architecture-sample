package com.lostsys.sample.hexagonal.application;

import com.lostsys.sample.hexagonal.domain.Orders;
import com.lostsys.sample.hexagonal.infra.inputport.OrderInputPort;
import com.lostsys.sample.hexagonal.infra.outputport.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class OrderUserCase implements OrderInputPort {

    @Autowired
    CommandRepository entityRepository;

    @Override
    public Orders createOrder(String customerId, BigDecimal total) {
        Orders order = Orders.builder()
                .id(UUID.randomUUID().toString())
                .customerId(customerId)
                .total(total)
                .build();

        return entityRepository.save(order);
    }

}

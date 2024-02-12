package com.bac.sample.hexagonal.application;

import com.bac.sample.hexagonal.domain.Orders;
import com.bac.sample.hexagonal.infra.inputport.OrderInputPort;
import com.bac.sample.hexagonal.infra.outputport.CommandRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class OrderUserCase implements OrderInputPort {

    final CommandRepository entityRepository;

    public OrderUserCase(CommandRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

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

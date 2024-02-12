package com.bac.sample.hexagonal.infra.input.port;

import com.bac.sample.hexagonal.domain.Orders;

import java.math.BigDecimal;

public interface OrderInputPort {
    Orders createOrder(String customerId, BigDecimal total);
}

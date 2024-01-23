package com.lostsys.sample.hexagonal.infra.inputport;

import com.lostsys.sample.hexagonal.domain.Orders;

import java.math.BigDecimal;

public interface OrderInputPort {
    Orders createOrder(String customerId, BigDecimal total);
}

package com.bac.sample.hexagonal.infra.inputport;

import com.bac.sample.hexagonal.domain.Customer;

import java.util.List;

public interface CustomerInputPort {

    Customer createCustomer(String name, String country);

    Customer getById(String customerId);

    List<Customer> getAll();

}

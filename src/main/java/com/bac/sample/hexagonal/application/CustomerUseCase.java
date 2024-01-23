package com.bac.sample.hexagonal.application;

import com.bac.sample.hexagonal.domain.Customer;
import com.bac.sample.hexagonal.infra.inputport.CustomerInputPort;
import com.bac.sample.hexagonal.infra.outputport.CommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CustomerUseCase implements CustomerInputPort {

    @Autowired
    CommandRepository entityRepository;

    @Override
    public Customer createCustomer(String name, String country) {
        Customer customer = Customer.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .country(country)
                .build();

        return entityRepository.save(customer);
    }

    @Override
    public Customer getById(String customerId) {
        return entityRepository.getById(customerId, Customer.class);
    }

    @Override
    public List<Customer> getAll() {
        return entityRepository.getAll(Customer.class);
    }

}

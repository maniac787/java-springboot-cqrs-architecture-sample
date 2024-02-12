package com.bac.sample.hexagonal.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bac.sample.hexagonal.domain.Customer;
import com.bac.sample.hexagonal.infra.input.port.CustomerInputPort;
import com.bac.sample.hexagonal.infra.output.port.CommandRepository;

@Component
public class CustomerUseCase implements CustomerInputPort {

    final CommandRepository entityRepository;

    public CustomerUseCase(CommandRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @Override
    public Customer createCustomer(String name, String country) {
        Customer customer = Customer.builder()
            .id( UUID.randomUUID().toString() )
            .name( name )
            .country( country )
            .build();

        return entityRepository.save( customer );
    }

    @Override
    public Customer getById(String customerId) {
        return entityRepository.getById( customerId, Customer.class );
    }

    @Override
    public List<Customer> getAll() {
        return entityRepository.getAll( Customer.class );
    }
    
}

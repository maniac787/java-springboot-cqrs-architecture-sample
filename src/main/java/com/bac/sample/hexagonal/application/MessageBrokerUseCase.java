package com.bac.sample.hexagonal.application;

import com.bac.sample.hexagonal.domain.Customer;
import com.bac.sample.hexagonal.domain.Orders;
import com.bac.sample.hexagonal.infra.input.port.MessageBrokerInputPort;
import com.bac.sample.hexagonal.infra.output.port.QueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MessageBrokerUseCase implements MessageBrokerInputPort {

    final QueryRepository queryRepository;

    Map<String, Class<?>> classes = Map.of(
            "customer", Customer.class,
            "orders", Orders.class
    );

    public MessageBrokerUseCase(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @Override
    public void deleteReg(String table, Map<String, Object> reg) {
        queryRepository.delete((String) reg.get("id"), classes.get(table));
    }

    @Override
    public void updateReg(String table, Map<String, Object> reg) {
        queryRepository.save(reg, classes.get(table));
    }

    @Override
    public void insertReg(String table, Map<String, Object> reg) {
        queryRepository.save(reg, classes.get(table));
    }

    @Override
    public List<Map<String, Object>> getAll(String table) {
        return queryRepository.getAll(classes.get(table));
    }

}

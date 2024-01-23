package com.bac.sample.hexagonal.infra.inputadapter.http;


import com.bac.sample.hexagonal.domain.Customer;
import com.bac.sample.hexagonal.infra.inputport.CustomerInputPort;
import com.bac.sample.hexagonal.infra.inputport.MessageBrokerInputPort;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "customer")
public class CustomerAPI {

    final CustomerInputPort customerInputPort;

    final MessageBrokerInputPort messageBrokerInputPort;

    final JdbcTemplate jdbcTemplate;

    public CustomerAPI(CustomerInputPort customerInputPort, MessageBrokerInputPort messageBrokerInputPort, JdbcTemplate jdbcTemplate) {
        this.customerInputPort = customerInputPort;
        this.messageBrokerInputPort = messageBrokerInputPort;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping(value = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer create(@RequestParam(value = "name") String name, @RequestParam(value = "country") String country) {
        return customerInputPort.createCustomer(name, country);
    }

    @PostMapping(value = "get", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer get(@RequestParam String customerId) {
        return customerInputPort.getById(customerId);
    }

    @GetMapping(value = "getall", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAll() {
        return customerInputPort.getAll();
    }

    @GetMapping(value = "getallCustomerCQRS", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getallCQRS() {
        return messageBrokerInputPort.getAll("customer");
    }

    @GetMapping(value = "getallCustomerNormal", produces = MediaType.APPLICATION_JSON_VALUE)
    public List getallCustomerNormal() {
        return jdbcTemplate.queryForList("SELECT *, (SELECT array_to_json(array_agg(row_to_json(t))) FROM (SELECT row_to_json(o) FROM Orders o WHERE o.customerId=c.id) t) AS orders FROM Customer c;");
    }
}

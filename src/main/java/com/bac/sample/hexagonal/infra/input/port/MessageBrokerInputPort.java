package com.bac.sample.hexagonal.infra.input.port;

import java.util.List;
import java.util.Map;

public interface MessageBrokerInputPort {

    void deleteReg(String table, Map<String, Object> reg);

    void updateReg(String table, Map<String, Object> reg);

    void insertReg(String table, Map<String, Object> reg);

    List<Map<String, Object>> getAll(String table);

}

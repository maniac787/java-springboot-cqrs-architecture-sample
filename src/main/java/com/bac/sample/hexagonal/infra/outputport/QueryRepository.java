package com.bac.sample.hexagonal.infra.outputport;

import java.util.List;
import java.util.Map;

public interface QueryRepository {

    void save(Map<String, Object> reg, Class<?> clazz);

    void delete(String id, Class<?> clazz);

    Map<String, Object> getById(String id, Class<?> clazz);

    List<Map<String, Object>> getAll(Class<?> clazz);
}

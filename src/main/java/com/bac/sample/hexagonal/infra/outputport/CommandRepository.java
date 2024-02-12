package com.bac.sample.hexagonal.infra.outputport;

import java.util.List;

public interface CommandRepository {

    <T> T save(T reg);

    <T> T getById(String id, Class<T> clazz);

    <T> List<T> getAll(Class<T> clazz);

}

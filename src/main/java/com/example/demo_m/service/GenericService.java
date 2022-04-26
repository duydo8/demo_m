package com.example.demo_m.service;

import java.util.List;
import java.util.Optional;

public interface GenericService <T>{
    T save (T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
    void deleteById(Long id);

}

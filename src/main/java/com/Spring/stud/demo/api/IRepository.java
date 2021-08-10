package com.Spring.stud.demo.api;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IRepository<K> {
    K findById(Long id);
    List<K> findAll();
    void deleteById(Long id);
    K saveOrUpdate(K k);
    void save(K k);
    void addCost(Long id);
    void reduceCost(Long id);
}

package com.Spring.stud.demo.api;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IRepository<K> {
    List<K> getAll ();
    K getById (Long id);
    void save(K k);
}

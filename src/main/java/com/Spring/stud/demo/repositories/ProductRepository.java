package com.Spring.stud.demo.repositories;

import com.Spring.stud.demo.api.IRepository;
import com.Spring.stud.demo.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository implements IRepository<Product> {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Meat", 999),
                new Product(2L, "Sugar", 30),
                new Product(3L, "Aqua", 15),
                new Product(4L, "Bread", 35)
        ));
    }

    @Override
    public List<Product> getAll() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public Product getById(Long id) {
        return products.stream().filter(p->p.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }
}

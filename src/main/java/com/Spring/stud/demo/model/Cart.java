package com.Spring.stud.demo.model;

import com.Spring.stud.demo.controllers.exceptions.ResourceNotFoundException;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Data
@Component
public class Cart {

    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addToCart(Product product) {
        products.add(product);
    }

    public void deleteFromCart(Long id) {
        products.remove(findById(id));
    }

    public void flushCart() {
        products.clear();
    }

    public Product findById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found!"));
    }

    public List<Product> findAll() {
        return products;
    }
}
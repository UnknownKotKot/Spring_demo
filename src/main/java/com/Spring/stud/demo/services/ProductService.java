package com.Spring.stud.demo.services;

import com.Spring.stud.demo.api.IRepository;
import com.Spring.stud.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private IRepository<Product> productRepository;

    @Autowired
    public ProductService(IRepository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product getById(Long id) {
        return productRepository.getById(id);
    }

}

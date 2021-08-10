package com.Spring.stud.demo.services;

import com.Spring.stud.demo.api.IRepository;
import com.Spring.stud.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceDAO {
    private IRepository<Product> productIRepository;

    @Autowired
    public ProductServiceDAO(@Qualifier("productRepositoryDAO") IRepository<Product> productIRepository) {
        this.productIRepository = productIRepository;
    }

    public Product findById(Long id) {
        return productIRepository.findById(id);
    }

    public List<Product> findAll() {
        return productIRepository.findAll();
    }

    public void deleteById(Long id) {
        productIRepository.deleteById(id);
    }

    public Product saveOrUpdate(Product product) {
        return productIRepository.saveOrUpdate(product);
    }

}

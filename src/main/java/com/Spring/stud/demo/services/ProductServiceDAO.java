package com.Spring.stud.demo.services;

import com.Spring.stud.demo.api.IEntity;
import com.Spring.stud.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceDAO {
    private IEntity<Product> productIEntity;

    @Autowired
    public ProductServiceDAO(@Qualifier("productDAO") IEntity<Product> productIEntity) {
        this.productIEntity = productIEntity;
    }

    public Product findById(Long id) {
        return productIEntity.findById(id);
    }

    public List<Product> findAll() {
        return productIEntity.findAll();
    }

    public void deleteById(Long id) {
        productIEntity.deleteById(id);
    }

    public Product saveOrUpdate(Product product) {
        return productIEntity.saveOrUpdate(product);
    }

}

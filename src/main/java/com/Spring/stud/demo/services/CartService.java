package com.Spring.stud.demo.services;

import com.Spring.stud.demo.controllers.exceptions.ResourceNotFoundException;
import com.Spring.stud.demo.model.Cart;
import com.Spring.stud.demo.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart cart;
    private ProductService productService;

    @Autowired
    public CartService(Cart cart, ProductService productService) {
        this.cart = cart;
        this.productService = productService;
    }

    public void addProduct(Long id) {
        cart.addToCart(productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found!")));
    }

    public void deleteById(Long id) {
        cart.deleteFromCart(id);
    }

    public void deleteAllProducts() {
        cart.flushCart();
    }

    public Product findById(Long id) {
        return cart.findById(id);
    }

    public List<Product> findAll() {
        return cart.findAll();
    }

}

package com.Spring.stud.demo.services;

import com.Spring.stud.demo.controllers.exceptions.ResourceNotFoundException;
import com.Spring.stud.demo.utils.Cart;
import com.Spring.stud.demo.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart cart;
    private final ProductService productService;

    @PostConstruct
    public void init() {
    this.cart = new Cart();
    }

    public void addProduct(Long id) {
        if (cart.addToCart(id)) {
            return;
        }
        Product product = productService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Failed to add product due to wrong or missed product ID"));
        cart.addToCart(product);
    }

    public void deleteById(Long id) {
        cart.deleteFromCart(id);
    }

    public void reduceById(Long id) {
        cart.reduce(id);
    }

    public void deleteAllProducts() {
        cart.flushCart();
    }

    public Cart getCartForCurrentUser() {
        return cart;
    }

    public void clearCart() {
        cart.flushCart();
    }

}

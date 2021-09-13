package com.Spring.stud.demo.controllers;

import com.Spring.stud.demo.utils.Cart;
import com.Spring.stud.demo.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCartForCurrentUser() {
        return cartService.getCartForCurrentUser();
    }

    @GetMapping("add/{id}")
    public void addById(@PathVariable Long id) {
        cartService.addProduct(id);
    }

    @GetMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        cartService.deleteById(id);
    }

    @GetMapping("reduce/{id}")
    public void reduceById(@PathVariable Long id) {
        cartService.reduceById(id);
    }

    @GetMapping("delete/all")
    public void deleteAll() {
        cartService.deleteAllProducts();
    }
}

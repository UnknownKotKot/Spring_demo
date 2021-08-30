package com.Spring.stud.demo.controllers;

import com.Spring.stud.demo.dto.ProductDto;
import com.Spring.stud.demo.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private CartService cartService;

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public List<ProductDto> findAll() {
        return cartService.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(cartService.findById(id));
    }

    @GetMapping("add/{id}")
    public void addById(@PathVariable Long id) {
        cartService.addProduct(id);
    }

    @GetMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        cartService.deleteById(id);
    }

    @GetMapping("delete/all")
    public void deleteAll() {
        cartService.deleteAllProducts();
    }
}

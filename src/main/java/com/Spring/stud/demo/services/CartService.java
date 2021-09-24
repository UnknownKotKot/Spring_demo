package com.Spring.stud.demo.services;

import com.Spring.stud.demo.controllers.exceptions.ResourceNotFoundException;
import com.Spring.stud.demo.utils.Cart;
import com.Spring.stud.demo.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final RedisTemplate<String, Object> redisTemplate;

    public Cart getCartForCurrentUser() {
        if (!redisTemplate.hasKey("cart")) {
            redisTemplate.opsForValue().set("cart", new Cart());
        }
        Cart cart = (Cart) redisTemplate.opsForValue().get("cart");
        return cart;
    }

    public void updateCart(Cart cart) {
        redisTemplate.opsForValue().set("cart", cart);
    }


    public void addProduct(Long id) {
        Cart cart = getCartForCurrentUser();
        if (cart.addToCart(id)) {
            updateCart(cart);
            return;
        }
        Product product = productService.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Failed to add product due to wrong or missed product ID"));
        cart.addToCart(product);
        updateCart(cart);
    }

    public void deleteById(Long id) {
        Cart cart = getCartForCurrentUser();
        cart.deleteFromCart(id);
        updateCart(cart);
    }

    public void reduceById(Long id) {
        Cart cart = getCartForCurrentUser();
        cart.reduce(id);
        updateCart(cart);
    }

    public void clearCart() {
        Cart cart = getCartForCurrentUser();
        cart.flushCart();
        updateCart(cart);
    }

    public boolean isCartExists() {
        return redisTemplate.hasKey("cart");
    }

}

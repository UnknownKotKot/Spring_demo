package com.Spring.stud.demo.services;

import com.Spring.stud.demo.controllers.exceptions.ResourceNotFoundException;
import com.Spring.stud.demo.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.UUID;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${utils.cart.prefix}")
    private String cartPrefix;

    public String getCartUuidFromSuffix(String suffix) {
        return cartPrefix + suffix;
    }

    public String generateCartUuid() {
        return UUID.randomUUID().toString();
    }

    public Cart getCurrentCart(String cartKey) {
        /* //flushing Redis cash
        Jedis jedis = new Jedis();
        jedis.connect();
        jedis.flushAll();
        jedis.disconnect();*/
        if (!redisTemplate.hasKey(cartKey)) {
            redisTemplate.opsForValue().set(cartKey, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);
    }

    public void addToCart(String cartKey, Long productId) {
        execute(cartKey, c -> {
            if (!c.addToCart(productId)) {
                c.addToCart(productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Unable add product to cart. Product not found id: " + productId)));
            }
        });
    }

    public void clearCart(String cartKey) {
        execute(cartKey, Cart::flushCart);
    }

    public void removeItemFromCart(String cartKey, Long productId) {
        execute(cartKey, c -> c.deleteFromCart(productId));
    }

    public void decrementItem(String cartKey, Long productId) {
        execute(cartKey, c -> c.reduce(productId));
    }

    public void merge(String userCartKey, String guestCartKey) {
        Cart guestCart = getCurrentCart(guestCartKey);
        Cart userCart = getCurrentCart(userCartKey);
        userCart.merge(guestCart);
        updateCart(guestCartKey, guestCart);
        updateCart(userCartKey, userCart);
    }

    private void execute(String cartKey, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartKey);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    public void updateCart(String cartKey, Cart cart) {
        redisTemplate.opsForValue().set(cartKey, cart);
    }

}

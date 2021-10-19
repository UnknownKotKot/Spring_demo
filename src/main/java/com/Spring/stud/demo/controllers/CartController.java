package com.Spring.stud.demo.controllers;

import com.Spring.stud.demo.dto.StringResponse;
import com.Spring.stud.demo.utils.Cart;
import com.Spring.stud.demo.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{uuid}")
    public Cart getCart(Principal principal, @PathVariable String uuid) {
        return cartService.getCurrentCart(getCurrentCartUuid(principal, uuid));
    }

    @GetMapping("/generate")
    public StringResponse getCart() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void add(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.addToCart(getCurrentCartUuid(principal, uuid), productId);
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrement(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.decrementItem(getCurrentCartUuid(principal, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    public void remove(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.removeItemFromCart(getCurrentCartUuid(principal, uuid), productId);
    }

    @GetMapping("/{uuid}/clear")
    public void clear(Principal principal, @PathVariable String uuid) {
        cartService.clearCart(getCurrentCartUuid(principal, uuid));
    }

    @GetMapping("/{uuid}/merge")
    public void merge(Principal principal, @PathVariable String uuid) {
        cartService.merge(
                getCurrentCartUuid(principal, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    private String getCurrentCartUuid(Principal principal, String uuid) {
        if (principal != null) {
            return cartService.getCartUuidFromSuffix(principal.getName());
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}

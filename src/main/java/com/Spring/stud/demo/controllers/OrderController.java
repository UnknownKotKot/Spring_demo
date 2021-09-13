package com.Spring.stud.demo.controllers;

import com.Spring.stud.demo.dto.OrderDetailsDto;
import com.Spring.stud.demo.dto.OrderItemDto;
import com.Spring.stud.demo.model.Order;
import com.Spring.stud.demo.model.OrderItem;
import com.Spring.stud.demo.services.CartService;
import com.Spring.stud.demo.services.OrderItemService;
import com.Spring.stud.demo.services.OrderService;
import com.Spring.stud.demo.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final CartService cartService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private Cart cart;

    @PostConstruct
    public void init() {
        this.cart = new Cart();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder (@RequestBody OrderDetailsDto orderDetailsDto) {
        cart = orderDetailsDto.getCart();
        Collection<OrderItemDto> orderItemDtoList = cart.findAll();

        Order order = new Order();
        order.setTelNumber(orderDetailsDto.getPhoneNumber());
        order.setAddress(orderDetailsDto.getAddress());
        order.setOrderTitle(orderDetailsDto.getOrderTitle());
        order.setOrderPrice(cart.getFullPrice());
        orderService.save(order);

        for (OrderItemDto orderItemDto : orderItemDtoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(orderItemDto.getProductId());
            orderItem.setProductTitle(orderItemDto.getProductTitle());
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setUnitPrice(orderItemDto.getUnitPrice());
            orderItem.setFullPrice(orderItemDto.getFullPrice());
            orderItem.setOrder(order);
            orderItemService.save(orderItem);
        }
        cartService.clearCart();
    }
}

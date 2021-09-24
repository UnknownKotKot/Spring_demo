package com.Spring.stud.demo.services;

import com.Spring.stud.demo.api.OrderRepository;
import com.Spring.stud.demo.controllers.exceptions.ResourceNotFoundException;
import com.Spring.stud.demo.dto.OrderDetailsDto;
import com.Spring.stud.demo.dto.OrderItemDto;
import com.Spring.stud.demo.model.Order;
import com.Spring.stud.demo.model.OrderItem;
import com.Spring.stud.demo.model.User;
import com.Spring.stud.demo.utils.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;

    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto) {
        User user = userService.findByUsername(username)
                .orElseThrow(()-> new ResourceNotFoundException("failed to find user while order confirmation: " + username));
        Cart cart = cartService.getCartForCurrentUser();
        Order order = new Order();
        order.setUser(user);
        order.setOrderPrice(cart.getFullPrice());
        order.setAddress(orderDetailsDto.getAddress());
        order.setTelNumber(orderDetailsDto.getPhoneNumber());
        List<OrderItem> items = new ArrayList<>();
        for (OrderItemDto o : cart.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setFullPrice(o.getFullPrice());
            orderItem.setUnitPrice(o.getUnitPrice());
            orderItem.setQuantity(o.getQuantity());
            orderItem.setProduct(productService.findById(o.getProductId())
                    .orElseThrow(()-> new ResourceNotFoundException("failed to find product while order confirmation: " + o.getProductId())));
            items.add(orderItem);
        }
        order.setOrderItemList(items);
        orderRepository.save(order);
        cartService.clearCart();
    }

    public List<Order> findAllByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }
}

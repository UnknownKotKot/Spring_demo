package com.Spring.stud.demo.controllers;

import com.Spring.stud.demo.dto.OrderDetailsDto;
import com.Spring.stud.demo.dto.OrderDto;
import com.Spring.stud.demo.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDetailsDto orderDetailsDto, Principal principal) {
        orderService.createOrder(principal.getName(), orderDetailsDto);
    }

    @GetMapping
    public List<OrderDto> getOrdersForCurrentUser(Principal principal) {
        return orderService.findAllByUsername(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}

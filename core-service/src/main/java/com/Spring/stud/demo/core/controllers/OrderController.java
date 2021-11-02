package com.Spring.stud.demo.core.controllers;

import com.Spring.stud.demo.api.dtos.OrderDetailsDto;
import com.Spring.stud.demo.api.dtos.OrderDto;
import com.Spring.stud.demo.core.services.OrderService;
import com.Spring.stud.demo.core.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Converter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDetailsDto orderDetailsDto, @RequestHeader String username) {
        orderService.createOrder(username, orderDetailsDto);
    }

    @GetMapping
    public List<OrderDto> getOrdersForCurrentUser(@RequestHeader String username) {

        return orderService.findAllByUsername(username).stream().map(o -> converter.orderToDto(o)).collect(Collectors.toList());
    }
}

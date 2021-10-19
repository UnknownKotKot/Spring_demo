package com.Spring.stud.demo.dto;

import com.Spring.stud.demo.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private List<OrderItemDto> items;
    private int price;
    private String phone;
    private String address;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.price = order.getPrice();
        this.phone = order.getPhone();
        this.address = order.getAddress();
    }
}



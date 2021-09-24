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
    private List<OrderItemDto> orderItemList;
    private int orderPrice;
    private String telNumber;
    private String address;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.orderItemList = order.getOrderItemList().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.orderPrice = order.getOrderPrice();
        this.telNumber = order.getTelNumber();
        this.address = order.getAddress();
    }
}



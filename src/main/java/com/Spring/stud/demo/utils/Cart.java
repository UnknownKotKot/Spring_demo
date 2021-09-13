package com.Spring.stud.demo.utils;

import com.Spring.stud.demo.dto.OrderItemDto;
import com.Spring.stud.demo.model.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Data
public class Cart {
    private List<OrderItemDto> orderItems;
    private int fullPrice;

    public Cart() {
        this.orderItems = new ArrayList<>();
    }

    public void addToCart(Product product) {
        orderItems.add(new OrderItemDto(product));
        refreshFullPrice();
    }

    public boolean addToCart(Long id) {
        for (OrderItemDto i : orderItems) {
            if (i.getProductId().equals(id)) {
                i.changeQuantity(1);
                refreshFullPrice();
                return true;
            }
        }
        return false;
    }

    public void reduce(Long id) {
        Iterator<OrderItemDto> iterator = orderItems.iterator();
        while (iterator.hasNext()) {
            OrderItemDto i = iterator.next();
            if (i.getProductId().equals(id)) {
                i.changeQuantity(-1);
                if (i.getQuantity()<=0) {
                    iterator.remove();
                }
                refreshFullPrice();
                return;
            }
        }
    }

    public void deleteFromCart(Long id) {
        orderItems.removeIf(i->i.getProductId().equals(id));
        refreshFullPrice();
    }

    public void refreshFullPrice() {
        fullPrice = 0;
        for (OrderItemDto i : orderItems) {
            fullPrice += i.getFullPrice();
        }
    }

    public void flushCart() {
        orderItems.clear();
        fullPrice = 0;
    }

    public List<OrderItemDto> findAll() {
        return orderItems;
    }



}
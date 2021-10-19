package com.Spring.stud.demo.utils;

import com.Spring.stud.demo.dto.OrderItemDto;
import com.Spring.stud.demo.model.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Data
public class Cart {
    private List<OrderItemDto> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addToCart(Product product) {
        items.add(new OrderItemDto(product));
        refreshFullPrice();
    }

    public boolean addToCart(Long id) {
        for (OrderItemDto i : items) {
            if (i.getProductId().equals(id)) {
                i.changeQuantity(1);
                refreshFullPrice();
                return true;
            }
        }
        return false;
    }

    public void reduce(Long id) {
        Iterator<OrderItemDto> iterator = items.iterator();
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
        items.removeIf(i->i.getProductId().equals(id));
        refreshFullPrice();
    }

    public void refreshFullPrice() {
        totalPrice = 0;
        for (OrderItemDto i : items) {
            totalPrice += i.getPrice();
        }
    }

    public void flushCart() {
        items.clear();
        totalPrice = 0;
    }

    public List<OrderItemDto> findAll() {
        return items;
    }

    public void merge(Cart another) {
        for (OrderItemDto anotherItem : another.items) {
            boolean merged = false;
            for (OrderItemDto myItem : items) {
                if (myItem.getProductId().equals(anotherItem.getProductId())) {
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        refreshFullPrice();
        another.flushCart();
    }

}
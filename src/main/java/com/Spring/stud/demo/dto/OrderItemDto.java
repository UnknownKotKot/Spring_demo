package com.Spring.stud.demo.dto;

import com.Spring.stud.demo.model.OrderItem;
import com.Spring.stud.demo.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private int unitPrice;
    private int fullPrice;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.quantity = 1;
        this.unitPrice = product.getPrice();
        this.fullPrice = product.getPrice();
    }

    public OrderItemDto(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.unitPrice = orderItem.getUnitPrice();
        this.fullPrice = orderItem.getFullPrice();
    }

    public void changeQuantity(int  multiplier) {
        quantity += multiplier;
        if (quantity<0) {
            quantity = 0;
        }
        fullPrice = unitPrice * quantity;
    }
}

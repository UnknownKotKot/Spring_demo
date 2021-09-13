package com.Spring.stud.demo.dto;

import com.Spring.stud.demo.utils.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsDto {
    private Cart cart;
    private String orderTitle;
    private String phoneNumber;
    private String address;
}

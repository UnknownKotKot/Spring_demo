package com.Spring.stud.demo.cart.integration;

import com.Spring.stud.demo.api.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integration.product-service.url}")
    private String productServiceUrl;

    public ProductDto getProductById(Long productId) {
        return restTemplate.getForObject(productServiceUrl + "/api/v1/products/" + productId, ProductDto.class);
    }
}

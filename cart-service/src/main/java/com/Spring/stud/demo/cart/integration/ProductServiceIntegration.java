package com.Spring.stud.demo.cart.integration;

import com.Spring.stud.demo.api.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    public ProductDto getProductById(Long productId) {
        return productServiceWebClient.get()
                .uri("/api/v1/products/" + productId)
                .retrieve()
                .bodyToMono(ProductDto.class)
                .block();
    }
}

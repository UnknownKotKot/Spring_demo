package com.Spring.stud.demo.services;

import com.Spring.stud.demo.api.ProductRepository;
import com.Spring.stud.demo.dto.ProductDto;
import com.Spring.stud.demo.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductDto> findAll(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize)).map(ProductDto::new);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findGreaterPrice(int minPrice) {
        return productRepository.findAllByPriceGreaterThanEqual(minPrice);
    }

    public List<Product> findLessPrice(int maxPrice) {
        return productRepository.findAllByPriceLessThanEqual(maxPrice);
    }

    public List<Product> findBetween(int minPrice, int maxPrice) {
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    public Optional<Product> findByTitle (String title) {
        return productRepository.findByTitle(title);
    }

}

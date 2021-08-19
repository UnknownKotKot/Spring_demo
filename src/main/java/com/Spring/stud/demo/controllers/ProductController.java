package com.Spring.stud.demo.controllers;

import com.Spring.stud.demo.dto.ProductDto;
import com.Spring.stud.demo.model.Category;
import com.Spring.stud.demo.model.Product;
import com.Spring.stud.demo.services.CategoryService;
import com.Spring.stud.demo.services.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/products/filter/max_price")
    public List<Product> findAllByMaxPrice(@RequestParam(name = "max_price") int maxPrice) {
        return productService.findLessPrice(maxPrice);
    }

    @GetMapping("/products/filter/min_price")
    public List<Product> findAllByMinPrice(@RequestParam(name = "min_price") int minPrice) {
        return productService.findGreaterPrice(minPrice);

    }

    @GetMapping("/products/filter/between_price")
    public List<Product> findAllByMaxPrice(@RequestParam(name = "min_price") int minPrice, @RequestParam(name = "max_price") int maxPrice) {
        return productService.findBetween(minPrice, maxPrice);
    }

    @GetMapping("/products/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).get());
    }

    @PostMapping("/products")
    public ProductDto save(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).get();
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }

    @GetMapping("/products/delete/{id}")
    public String deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return "deleted successfully";
    }

}

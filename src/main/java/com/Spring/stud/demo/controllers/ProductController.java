package com.Spring.stud.demo.controllers;

import com.Spring.stud.demo.controllers.exceptions.ResourceNotFoundException;
import com.Spring.stud.demo.dto.ProductDto;
import com.Spring.stud.demo.model.Category;
import com.Spring.stud.demo.model.Product;
import com.Spring.stud.demo.services.CategoryService;
import com.Spring.stud.demo.services.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "0", name = "p") int pageIndex, @RequestParam(defaultValue = "10", name = "s") int pageSize) {
        return productService.findAll(pageIndex, pageSize);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found!"));
        return new ProductDto(product);
    }

    @PutMapping()
    public void update(@RequestBody ProductDto productDto) {
        Long id = productDto.getId();
        if (productService.findById(id).isPresent()) {
            String title = productDto.getTitle();
            int price = productDto.getPrice();
            String category = productDto.getCategoryTitle();
            Product product = productService.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found!"));
            if (title != null)
                product.setTitle(title);
            if (price > 0)
                product.setPrice(price);
            if (category != null)
                product.setCategory(categoryService.findByTitle(category)
                        .orElseThrow(() -> new ResourceNotFoundException("Category id = " + productDto.getCategoryTitle() + " not found!")));
            productService.save(product);
        } else throw new ResourceNotFoundException("Product id = " + id + " not found!");

    }

    @PostMapping()
    public ProductDto save(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Category id = " + productDto.getCategoryTitle() + " not found!"));
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
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

}

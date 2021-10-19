package com.Spring.stud.demo.controllers;

import com.Spring.stud.demo.controllers.exceptions.ResourceNotFoundException;
import com.Spring.stud.demo.dto.ProductDto;
import com.Spring.stud.demo.model.Category;
import com.Spring.stud.demo.model.Product;
import com.Spring.stud.demo.services.CategoryService;
import com.Spring.stud.demo.services.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public Page<ProductDto> findAll(
            @RequestParam(defaultValue = "1", name = "p") int pageIndex,
            @RequestParam MultiValueMap<String, String> params
    ) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10, params).map(ProductDto::new);
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found!"));
        return new ProductDto(product);
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

    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProductFromDto(productDto);
    }

}

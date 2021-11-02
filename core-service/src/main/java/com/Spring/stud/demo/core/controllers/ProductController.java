package com.Spring.stud.demo.core.controllers;

import com.Spring.stud.demo.api.dtos.ProductDto;
import com.Spring.stud.demo.api.exceptions.ResourceNotFoundException;
import com.Spring.stud.demo.core.controllers.exceptions.DataValidationException;
import com.Spring.stud.demo.core.model.Category;
import com.Spring.stud.demo.core.model.Product;
import com.Spring.stud.demo.core.services.CategoryService;
import com.Spring.stud.demo.core.services.ProductService;
import com.Spring.stud.demo.core.utils.Converter;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final Converter converter;

    @GetMapping
    public Page<ProductDto> findAll(
            @RequestParam(defaultValue = "1", name = "p") int pageIndex,
            @RequestParam MultiValueMap<String, String> params
    ) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10, params).map(p -> converter.productToDto(p));
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found"));
        return converter.productToDto(p);
    }

    @PostMapping
    public ProductDto save(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category title = " + productDto.getCategoryTitle() + " not found"));
        product.setCategory(category);
        productService.save(product);
        return converter.productToDto(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProductFromDto(productDto);
    }
}

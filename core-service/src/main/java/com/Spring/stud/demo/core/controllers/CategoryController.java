package com.Spring.stud.demo.core.controllers;

import com.Spring.stud.demo.api.dtos.CategoryDto;
import com.Spring.stud.demo.api.exceptions.ResourceNotFoundException;
import com.Spring.stud.demo.core.services.CategoryService;
import com.Spring.stud.demo.core.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {
    private final CategoryService categoryService;
    private final Converter converter;

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return converter.categoryToDto(categoryService.findByIdWithProducts(id).orElseThrow(() -> new ResourceNotFoundException("Category id = " + id + " not found")));
    }
}

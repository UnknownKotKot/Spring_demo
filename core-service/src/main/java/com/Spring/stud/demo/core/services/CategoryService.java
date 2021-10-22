package com.Spring.stud.demo.core.services;

import com.Spring.stud.demo.core.api.CategoryRepository;
import com.Spring.stud.demo.core.api.ProductRepository;
import com.Spring.stud.demo.core.model.Category;
import com.Spring.stud.demo.core.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByIdWithProducts(Long id) {
        return categoryRepository.findByIdWithProducts(id);
    }
}


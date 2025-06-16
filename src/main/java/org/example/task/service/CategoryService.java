package org.example.task.service;


import org.example.task.model.Category;
import org.example.task.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

public Category updateCategory(Long id, Category newCategory) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        existingCategory.setCategoryType(newCategory.getCategoryType());

        return categoryRepository.save(existingCategory);
    }


    public Optional<Category> findByCategoryType(String categoryType) {
        return categoryRepository.findByCategoryType(categoryType);
    }


}

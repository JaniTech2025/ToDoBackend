package org.example.task.controller;

import org.example.task.model.Category;
import org.example.task.repository.CategoryRepository;
import org.example.task.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    // @PostMapping
    @PostMapping(consumes = { "application/json" })
    public Category addCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id,
            @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> deleteCategory(
            @PathVariable Long id,
            @RequestBody Category category) {
        Category deletedCategory = categoryService.deleteCategory(id);
        return ResponseEntity.ok(deletedCategory);
    }

}
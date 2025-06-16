package org.example.task.repository;
import org.example.task.model.Category;
import org.example.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface  CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryType(String categoryType);
}


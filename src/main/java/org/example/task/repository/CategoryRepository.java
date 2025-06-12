package org.example.task.repository;
import org.example.task.model.Category;
import org.example.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CategoryRepository extends JpaRepository<Category, Long> {
}


package org.example.task.config;

import jakarta.transaction.Transactional;
import org.example.task.model.Category;
import org.example.task.model.Task;
import org.example.task.repository.CategoryRepository;
import org.example.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StartupDataLoader {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public StartupDataLoader(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void loadInitialData() {
        List<Category> categories = categoryRepository.findAll();
        List<Task> tasks = taskRepository.findAll();

        if (categories.size() >= 2 && tasks.size() >= 2) {
            Task task1 = tasks.get(0);
            task1.addCategory(categories.get(0)); // use helper method that adds to both sides
            task1.addCategory(categories.get(1));

            Task task2 = tasks.get(1);
            task2.addCategory(categories.get(1));

            // Save tasks to persist join table entries
            taskRepository.save(task1);
            taskRepository.save(task2);
        }
    }
}

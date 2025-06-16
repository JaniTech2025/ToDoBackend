package org.example.task.service;

import org.example.task.DTO.TaskDTO;
import org.example.task.model.Category;
import org.example.task.model.Task;
import org.example.task.repository.CategoryRepository;
import org.example.task.repository.TaskRepository;
import org.example.task.validation.ResourceNotFoundException;

import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public TaskService(TaskRepository taskRepository, CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTaskWithCategories(Long id) {
        return taskRepository.findByIdWithCategories(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task updateTaskFromDTO(Long id, TaskDTO dto) {
        Task existingTask = taskRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));

        existingTask.setTaskName(dto.getTaskName());
        existingTask.setDueDate(dto.getDueDate());
        existingTask.setCompleted(dto.getIsCompleted());
        existingTask.setArchived(dto.getIsArchived());

        if (dto.getCategoryTypes() != null) {
            Set<Category> categories = dto.getCategoryTypes().stream()
                    .map(type -> categoryRepository.findByCategoryType(type)
                            .orElseThrow(() -> new RuntimeException("Category not found: " + type)))
                    .collect(Collectors.toSet());
            existingTask.setCategories(categories);
        } else {
            existingTask.getCategories().clear();
        }

        return taskRepository.save(existingTask);
    }
    }




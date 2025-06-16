package org.example.task.mapper;

import org.example.task.DTO.TaskDTO;
import org.example.task.model.Category;
import org.example.task.model.Task;
import org.example.task.service.CategoryService;
import org.springframework.stereotype.Service;
import org.example.task.service.TaskService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskMapper {

    private final CategoryService categoryService;

    public TaskMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Task toEntity(TaskDTO dto) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTaskName(dto.getTaskName());
        task.setDueDate(dto.getDueDate());
        task.setCompleted(dto.getIsCompleted());
        task.setArchived(dto.getIsArchived());

        if (dto.getCategoryTypes() != null) {
            Set<Category> categories = dto.getCategoryTypes().stream()
                    .map(type -> categoryService.findByCategoryType(type)
                            .orElseThrow(() -> new RuntimeException("Category not found: " + type)))
                    .collect(Collectors.toSet());
            task.setCategories(categories);
        }
        return task;
    }

    public TaskDTO toDto(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTaskName(task.getTaskName());
        dto.setDueDate(task.getDueDate());
        dto.setIsCompleted(task.isCompleted());
        dto.setIsArchived(task.isArchived());

        if (task.getCategories() != null) {
            Set<String> categoryTypes = task.getCategories().stream()
                    .map(Category::getCategoryType)
                    .collect(Collectors.toSet());
            dto.setCategoryTypes(categoryTypes);
        }
        return dto;
    }
}

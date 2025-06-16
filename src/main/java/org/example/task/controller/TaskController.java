package org.example.task.controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.task.DTO.TaskDTO;
import org.example.task.model.Category;
import org.example.task.model.Task;
import org.example.task.repository.CategoryRepository;
import org.example.task.repository.TaskRepository;
import org.example.task.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TaskController {

    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final CategoryRepository categoryRepository;


    public TaskController(TaskRepository taskRepository, TaskService taskService, CategoryRepository categoryRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public List<Task> getTasksByCategory(@RequestParam(required = false) List<String> category) {
        if (category != null && !category.isEmpty()) {
            return taskRepository.findByCategoryTypeIn(category);
        } else {
            return taskRepository.findAll();
        }
    }


    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Map<String, Object> payload) {
        String taskName = (String) payload.get("taskName");
        String dueDateStr = (String) payload.get("dueDate");
        Boolean isCompleted = (Boolean) payload.get("isCompleted");
        Boolean isArchived = (Boolean) payload.get("isArchived");
        List<String> categoryTypes = (List<String>) payload.get("categoryTypes");

        LocalDate dueDate = LocalDate.parse(dueDateStr);

        Set<Category> categories = categoryTypes.stream()
                .map(ct -> categoryRepository.findByCategoryType(ct)
                        .orElseThrow(() -> new EntityNotFoundException("Category not found: " + ct)))
                .collect(Collectors.toSet());

        Task task = new Task();
        task.setTaskName(taskName);
        task.setDueDate(dueDate);
        task.setCompleted(isCompleted != null ? isCompleted : false);
        task.setArchived(isArchived != null ? isArchived : false);
        task.setCategories(categories);

        Task savedTask = taskService.saveTask(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task task = taskService.getTaskWithCategories(id);
        return ResponseEntity.ok(task);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> updateTask(
            @PathVariable Long id,
            @RequestBody TaskDTO taskDTO
    ) {
        Task updatedTask = taskService.updateTaskFromDTO(id, taskDTO);
        return ResponseEntity.ok(updatedTask);
    }


}
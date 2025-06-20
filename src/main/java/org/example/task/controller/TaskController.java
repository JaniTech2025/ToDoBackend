package org.example.task.controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.task.DTO.TaskDTO;
import org.example.task.DTO.responses.TaskResponse;
import org.example.task.model.Category;
import org.example.task.model.Task;
import org.example.task.repository.CategoryRepository;
import org.example.task.repository.TaskRepository;
import org.example.task.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
// import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TaskController {

    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final CategoryRepository categoryRepository;

    public TaskController(TaskRepository taskRepository, TaskService taskService,
            CategoryRepository categoryRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    // @GetMapping
    //
    // public List<Task> getTasksByCategory(@RequestParam(required = false)
    // List<String>
    // category) {
    // category != null && !category.isEmpty()) {
    // return taskRepository.findByCategoryTypeIn(category);
    // se {
    // return taskRepository.findAll();
    //

    @GetMapping
    public TaskResponse getTasks() {
        List<Task> tasks = taskRepository.findAllTasksWithCategories();
        return new TaskResponse(tasks);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Map<String, Object> payload) {
        String taskName = (String) payload.get("taskName");
        String dueDateStr = (String) payload.get("dueDate");
        Boolean isCompleted = (Boolean) payload.get("isCompleted");
        Boolean isArchived = (Boolean) payload.get("isArchived");
        ObjectMapper mapper = new ObjectMapper();
        List<String> categoryTypes = mapper.convertValue(payload.get("categoryTypes"),
                new TypeReference<List<String>>() {
                });

        LocalDate dueDate = LocalDate.parse(dueDateStr);

        Set<Category> categories = categoryTypes.stream()
                .map(ct -> categoryRepository.findByCategoryType(ct)
                        .orElseThrow(() -> new EntityNotFoundException("Category not found: " + ct)))
                .collect(Collectors.toSet());

        Task task = new Task();
        task.setTaskName(taskName);
        task.setDueDate(dueDate);
        task.setCompleted(Optional.ofNullable(isCompleted).orElse(false));
        task.setArchived(Optional.ofNullable(isArchived).orElse(false));
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
    public ResponseEntity<?> updateTask(
            @PathVariable Long id,
            @RequestBody TaskDTO taskDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Task updatedTask = taskService.updateTaskFromDTO(id, taskDTO);

        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        Task deleteTask = taskService.deleteTask(id);
        return ResponseEntity.ok(deleteTask);
    }

}
package org.example.task.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class TaskDTO {
    private Long id;

    @NotNull(message = "Task name is required")
    private String taskName;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    @NotNull(message = "isCompleted is required")
    private Boolean isCompleted;

    @NotNull(message = "isArchived is required")
    public Boolean isArchived;

    private Set<String> categoryTypes;
}

package org.example.task.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class TaskDTO {
    private Long id;

    @NotNull(message = "Task name is required")
    private String taskName;

    @NotNull(message = "Due date is required")
    private LocalDate dueDate;

    @JsonProperty("isCompleted")
    @NotNull(message = "isCompleted is required")
    private Boolean isCompleted;

    @JsonProperty("isArchived")
    @NotNull(message = "isArchived is required")
    private Boolean isArchived;

    private Set<String> categoryTypes;

}
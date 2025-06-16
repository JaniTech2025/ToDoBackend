package org.example.task.DTO;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class TaskDTO {
    private Long id;
    private String taskName;
    private LocalDate dueDate;
    private Boolean isCompleted;
    public Boolean isArchived;
    private Set<String> categoryTypes;
}

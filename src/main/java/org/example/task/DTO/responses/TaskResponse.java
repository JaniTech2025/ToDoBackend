package org.example.task.DTO.responses;

import java.util.List;

import org.example.task.model.Task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {
    private List<Task> tasks;

    public TaskResponse(List<Task> tasks) {
        this.tasks = tasks;
    }

}

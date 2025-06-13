package org.example.task.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@Entity
public class Task {
    @Id
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 50, name="taskName")
    private String taskName;

    @Column(nullable = false, name="duedate")
    private LocalDate dueDate;

    @Column(nullable = false, name="isCompleted")
    private boolean isCompleted;

    @Column(nullable = false, name="isArchived")
    private boolean isArchived;

    @ManyToMany
    @JoinTable(
            name = "task_category",
            joinColumns = @JoinColumn(name = "taskID"),
            inverseJoinColumns = @JoinColumn(name = "categoryID")
    )

    @JsonManagedReference
    private Set<Category> categories =  new HashSet<>();;
}

package org.example.task.model;

// import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

// import com.fasterxml.jackson.annotation.JsonManagedReference;

@Setter
@Getter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, length = 50, name = "taskName")
    private String taskName;

    @Column(nullable = false, name = "duedate")
    private LocalDate dueDate;

    @Column(nullable = false, name = "isCompleted")
    private boolean isCompleted;

    @Column(nullable = false, name = "isArchived")
    private boolean isArchived;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "task_category", joinColumns = @JoinColumn(name = "taskID"), inverseJoinColumns = @JoinColumn(name = "categoryID"))

    // @JsonIgnore
    // @JsonManagedReference(value = "task-category")
    private Set<Category> categories = new HashSet<>();

    public void addCategory(Category category) {
        categories.add(category);
        category.getTasks().add(this);
    }

    @Column(name = "overDue")
    public boolean isoverDue() {
        LocalDate today = LocalDate.now();
        return (dueDate.isBefore(today));
    }

}

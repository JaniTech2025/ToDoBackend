package org.example.task.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "categoryID")
    private Long categoryID;

    @Column(nullable = false, name = "categoryType", unique = true, length = 50)
    private String categoryType;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    @JsonBackReference(value = "task-category")
    private Set<Task> tasks = new HashSet<>();
}

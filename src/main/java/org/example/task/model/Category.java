package org.example.task.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Setter
@Getter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, name="categoryID")
    private Long categoryID;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "categorytype")
    private CategoryType categorytype;

    @ManyToMany(mappedBy = "categories")
    private Set<Task> tasks;
}

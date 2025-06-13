package org.example.task.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@Entity
public class Category {
    @Id
    @Column(unique = true, nullable = false, name="categoryID")
    private Long categoryID;

    @Column(nullable = false, name = "categoryType", unique = true,length = 50)
    private String categorytype;

    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private Set<Task> tasks =  new HashSet<>();;
}

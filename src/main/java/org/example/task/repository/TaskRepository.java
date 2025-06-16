package org.example.task.repository;
import org.example.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("SELECT t FROM Task t LEFT JOIN FETCH t.categories WHERE t.id = :id")
    Optional<Task> findByIdWithCategories(@Param("id") Long id);

    @Query("SELECT t FROM Task t JOIN t.categories c WHERE c.categoryType in :categoryType")
    List<Task> findByCategoryTypeIn(@Param("categoryType") List<String> category);

}

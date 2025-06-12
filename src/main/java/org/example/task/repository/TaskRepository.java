package org.example.task.repository;
import org.example.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Integer> {

}

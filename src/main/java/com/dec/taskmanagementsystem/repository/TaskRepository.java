package com.dec.taskmanagementsystem.repository;

import com.dec.taskmanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface for handles database operations
 * @author Hemant
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/" target="_blank">Java Docs</a>
 */

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByUserId(Long id);
}
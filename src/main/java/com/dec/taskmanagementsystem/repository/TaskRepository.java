package com.dec.taskmanagementsystem.repository;

import com.dec.taskmanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByUserId(Long id);
}
package com.dec.taskmanagementsystem.service;

import com.dec.taskmanagementsystem.dto.ApiResponse;
import com.dec.taskmanagementsystem.entity.Task;

import java.util.List;

public interface TaskService {
    ApiResponse createTask(Task task, Long userId);

    ApiResponse getTaskById(Integer taskId);

    List<Task> getAllTasks(Long userId);

    ApiResponse updateTask(Task task, Integer id);

    public void deleteTask(Integer id);

    ApiResponse doneTask(Integer id);

    ApiResponse pendingTask(Integer id);
}

package com.dec.taskmanagementsystem.service.impl;

import com.dec.taskmanagementsystem.dto.ApiResponse;
import com.dec.taskmanagementsystem.entity.Task;
import com.dec.taskmanagementsystem.entity.User;
import com.dec.taskmanagementsystem.exception.ResourceNotFoundException;
import com.dec.taskmanagementsystem.repository.TaskRepository;
import com.dec.taskmanagementsystem.repository.UserRepository;
import com.dec.taskmanagementsystem.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @Override
    public ApiResponse createTask(Task task, Long userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not Found, Id: " + userId));
        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        return new ApiResponse("Task saved!", savedTask);
    }

    @Override
    public ApiResponse getTaskById(Integer taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task Not Found, Id: " + taskId));

        return new ApiResponse("Found task", task);
    }

    @Override
    public List<Task> getAllTasks(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not Found, Id: " + userId));
        List<Task> taskList = taskRepository.findAllByUserId(user.getId());

        return taskList;
    }

    @Override
    public ApiResponse updateTask(Task task, Integer id) {
        Task foundTask = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found, Id: " + id));
        foundTask.setTask(task.getTask());
        foundTask.setCompleted(task.getCompleted());
        Task updatedTask = taskRepository.save(foundTask);

        return new ApiResponse("Task updated!", updatedTask);
    }

    @Override
    public void deleteTask(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found, Id: " + id));
        taskRepository.delete(task);
    }

    @Override
    public ApiResponse doneTask(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found, Id: " + id));
        task.setCompleted(true);

        return new ApiResponse("Task done", taskRepository.save(task));
    }

    @Override
    public ApiResponse pendingTask(Integer id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task Not Found, Id: " + id));
        task.setCompleted(false);
        return new ApiResponse("Task done", taskRepository.save(task));
    }
}

package com.dec.taskmanagementsystem.controller;

import com.dec.taskmanagementsystem.dto.ApiResponse;
import com.dec.taskmanagementsystem.entity.Task;
import com.dec.taskmanagementsystem.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class manages tasks like Creation, Deletion, Update, and Read.
 * @author Hemant
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/" target="_blank">Java Docs</a>
 */

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskService service;

    /**
     * handles Task create request
     * @param task contains values such as String 'task' and boolean 'completed'
     * @param userId Long value for find user
     * @return the ApiResponse
     */
    @PostMapping("/user/{id}")
    public ResponseEntity<ApiResponse> createTask(@Valid @RequestBody Task task, @PathVariable("id") Long userId){
        return new ResponseEntity<>(service.createTask(task, userId), HttpStatus.CREATED);
    }

    /**
     * handles to get all existing task
     * @return the List of Task object
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable("id") Long userId){
        return new ResponseEntity<>(service.getAllTasks(userId), HttpStatus.OK);
    }

    /**
     * handles to get a task by it's id
     * @param taskId Integer value for find task
     * @return the ApiResponse
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<ApiResponse> getTaskById(@PathVariable Integer taskId){
        return new ResponseEntity<>(service.getTaskById(taskId), HttpStatus.OK);
    }

    /**
     * handles to update the existing task
     * @param id Integer value
     * @param task object of Task
     * @return String value
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTask(@PathVariable Integer id,@Valid @RequestBody Task task){
        return new ResponseEntity<>(service.updateTask(task, id), HttpStatus.OK);
    }

    /**
     * handles request to delete a task by Id
     * @param id Integer value
     * @return String value
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Integer id){
        service.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

    /**
     * handles to set task is done
     *
     * @param id Integer value
     * @return the Task object
     */
    @PatchMapping("/{id}/task-done")
    public ResponseEntity<ApiResponse> completedTodo(@PathVariable Integer id){
        return ResponseEntity.ok(service.doneTask(id));
    }

    /**
     * handles to set task is pending
     *
     * @param id Integer value
     * @return the Task object
     */
    @PatchMapping("/{id}/task-pending")
    public ResponseEntity<ApiResponse> inCompletedTodo(@PathVariable Integer id){
        return ResponseEntity.ok(service.pendingTask(id));
    }
}

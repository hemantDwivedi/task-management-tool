package com.dec.taskmanagementsystem.service;

import com.dec.taskmanagementsystem.dto.ApiResponse;
import com.dec.taskmanagementsystem.entity.Task;

import java.util.List;

/**
 * Interface contains several methods for Task.
 * @author Hemant
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/" target="_blank">Java Docs</a>
 */

public interface TaskService {
    /**
     * An abstract method for creating task
     *
     * @param task Task object
     * @param userId for user
     * @return the Task
     */
    ApiResponse createTask(Task task, Long userId);

    /**
     * An abstract method for get task by its id
     * @param taskId Integer value for task
     * @return the Task matches with id
     */
    ApiResponse getTaskById(Integer taskId);

    /**
     * An abstract method which retrieve all Tasks
     * @param userId for user
     * @return the List all existing Tasks
     */
    List<Task> getAllTasks(Long userId);

    /**
     * An abstract method to perform update operation on task
     *
     * @param task Task object
     * @param id   Integer - task id
     * @return the updated task
     */
    ApiResponse updateTask(Task task, Integer id);

    /**
     * An abstract method to delete a task
     * @param id Integer - task id
     */
    public void deleteTask(Integer id);

    /**
     * An abstract method to set task is done
     *
     * @param id Integer - task id
     * @return the Task Object
     */
    ApiResponse doneTask(Integer id);

    /**
     * An abstract method to set task is pending
     *
     * @param id Integer - task id
     * @return the Task object
     */
    ApiResponse pendingTask(Integer id);
}

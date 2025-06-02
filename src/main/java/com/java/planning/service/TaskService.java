package com.java.planning.service;

import com.java.planning.entity.Task;

import java.util.List;
public interface TaskService {
    Task createTask(Task task);
    Task getTaskById(Long id);
    Task updateTask(Task task);
    void deleteTask(Long id);
    List<Task> getAllTasks();
}

package com.java.planning.facade;

import com.java.planning.dto.CreateTaskRequest;
import com.java.planning.dto.TaskDto;

import java.util.List;

public interface TaskFacade {
    TaskDto createTask(CreateTaskRequest request);
    TaskDto getTaskById(Long id);
    TaskDto updateTask(Long id, TaskDto dto);
    void deleteTask(Long id);
    List<TaskDto> getAllTasks();
}
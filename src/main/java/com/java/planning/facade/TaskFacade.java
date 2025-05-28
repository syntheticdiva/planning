package com.java.planning.facade;

import com.java.planning.dto.CreateTaskRequest;
import com.java.planning.dto.TaskDto;
import com.java.planning.entity.Task;
import com.java.planning.mapper.TaskMapper;
import com.java.planning.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskFacade {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskDto createTask(CreateTaskRequest request) {
        return taskMapper.toDto(
                taskService.createTask(
                        taskMapper.toEntity(request)
                )
        );
    }

    public TaskDto getTaskById(Long id) {
        return taskMapper.toDto(taskService.getTaskById(id));
    }

    public TaskDto updateTask(Long id, TaskDto dto) {
        Task existingTask = taskService.getTaskById(id);
        taskMapper.updateEntity(existingTask, dto);
        return taskMapper.toDto(taskService.updateTask(existingTask));
    }

    public void deleteTask(Long id) {
        taskService.deleteTask(id);
    }

    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks().stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
    }
}
package com.java.planning.controller;

import com.java.planning.dto.CreateTaskRequest;
import com.java.planning.dto.TaskDto;
import com.java.planning.facade.TaskFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskFacade taskFacade;
    @Operation(
            summary = "Создать задачу",
            description = "Создает новую задачу на основе переданных данных"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Задача успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(@RequestBody @Valid CreateTaskRequest request) {
        return taskFacade.createTask(request);
    }

    @Operation(
            summary = "Получить задачу по ID",
            description = "Возвращает задачу по её идентификатору"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Задача найдена"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена")
    })
    @GetMapping("/{id}")
    public TaskDto getById(@PathVariable Long id) {
        return taskFacade.getTaskById(id);
    }

    @Operation(
            summary = "Обновить задачу",
            description = "Обновляет существующую задачу по ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Задача обновлена"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена")
    })
    @PutMapping("/{id}")
    public TaskDto update(@PathVariable Long id, @RequestBody TaskDto dto) {
        return taskFacade.updateTask(id, dto);
    }

    @Operation(
            summary = "Удалить задачу",
            description = "Удаляет задачу по её идентификатору"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Задача удалена"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskFacade.deleteTask(id);
    }

    @Operation(
            summary = "Получить все задачи",
            description = "Возвращает список всех задач"
    )
    @ApiResponse(responseCode = "200", description = "Список задач успешно получен")
    @GetMapping
    public List<TaskDto> getAll() {
        return taskFacade.getAllTasks();
    }
}
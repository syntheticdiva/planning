package com.java.planning.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на создание задачи")
public class CreateTaskRequest {

    @NotBlank(message = "Title is mandatory")
    @Size(max = 200)
    @Schema(
            description = "Название задачи",
            example = "Реализовать задачу",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 200
    )
    private String title;

    @Size(max = 1000)
    @Schema(
            description = "Подробное описание задачи",
            example = "Необходимо добавить новую функцию",
            maxLength = 1000
    )
    private String description;
}
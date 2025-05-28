package com.java.planning.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Запрос на создание задачи")
public class CreateTaskRequest {

    @Schema(
            description = "Название задачи",
            example = "Реализовать задачу",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 255
    )
    @NotBlank(message = "Title is mandatory")
    private String title;

    @Schema(
            description = "Подробное описание задачи",
            example = "Необходимо добавить новую фуункцию"
    )
    private String description;

}
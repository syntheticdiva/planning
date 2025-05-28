package com.java.planning.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Детализированное представление задачи с комментариями")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskDto {

    @Schema(
            description = "Уникальный идентификатор задачи",
            example = "123",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Schema(
            description = "Название задачи",
            example = "Реализация задачи",
            requiredMode = Schema.RequiredMode.REQUIRED,
            maxLength = 255
    )
    private String title;

    @Schema(
            description = "Подробное описание задачи",
            example = "Необходимо добавить новую функцию"
    )
    private String description;

}

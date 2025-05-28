package com.java.planning.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO для обновления данных пользователя")
public class UserUpdateDto {
    @Schema(description = "Логин пользователя", example = "new_user123")
    @NotBlank(message = "Login cannot be blank")
    private String login;

    @Schema(description = "Имя пользователя", example = "Иван Иванов")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Schema(description = "Электронная почта", example = "new.email@example.com")
    @NotBlank(message = "Email cannot be blank")
    private String email;
}
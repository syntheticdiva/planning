package com.java.planning.controller;

import com.java.planning.dto.AuthRequest;
import com.java.planning.dto.AuthResponse;
import com.java.planning.dto.RegisterRequest;
import com.java.planning.dto.UserDto;
import com.java.planning.facade.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserFacade userFacade;

    @Operation(
            summary = "Регистрация пользователя",
            description = "Создает нового пользователя с ролью USER"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Пользователь успешно зарегистрирован"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные (например, email уже занят)")
    })
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@RequestBody @Valid RegisterRequest request) {
        return userFacade.registerUser(request);
    }

    @Operation(
            summary = "Регистрация администратора",
            description = "Создает нового пользователя с ролью ADMIN"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Администратор успешно зарегистрирован"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен")
    })
    @PostMapping("/register_admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerAdmin(@RequestBody @Valid RegisterRequest request) {
        return userFacade.registerAdmin(request);
    }

    @Operation(
            summary = "Аутентификация",
            description = "Вход в систему по email и паролю. Возвращает JWT-токен."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успешный вход"),
            @ApiResponse(responseCode = "401", description = "Неверные учетные данные")
    })
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return userFacade.login(request);
    }
}
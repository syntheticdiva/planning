package com.java.planning.controller;

import com.java.planning.dto.UserDto;
import com.java.planning.dto.UserUpdateDto;
import com.java.planning.facade.UserFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @Operation(
            summary = "Получить пользователя по ID",
            description = "Возвращает данные пользователя по его идентификатору"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userFacade.getUserById(id);
    }

    @Operation(
            summary = "Обновить данные пользователя",
            description = "Обновляет информацию о пользователе по ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Данные обновлены"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PutMapping("/{id}")
    public UserDto update(
            @PathVariable Long id,
            @RequestBody UserUpdateDto dto
    ) {
        return userFacade.updateUser(id, dto);
    }

    @Operation(
            summary = "Удалить пользователя",
            description = "Удаляет пользователя по ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Пользователь удален"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userFacade.deleteUser(id);
    }
}
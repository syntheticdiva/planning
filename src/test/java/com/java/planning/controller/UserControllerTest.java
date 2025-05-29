package com.java.planning.controller;

import com.java.planning.BaseTest;
import com.java.planning.dto.UserUpdateDto;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;

class UserControllerTest extends BaseTest {

    @Test
    void getUserById_ExistingUser_Returns200() {
        Long userId = createTestUser();

        given()
                .header("Authorization", "Bearer " + token)
                .get("/api/users/" + userId)
                .then()
                .statusCode(200)
                .body("id", equalTo(userId.intValue()))
                .body("login", notNullValue())
                .body("email", notNullValue());
    }

    @Test
    void updateUser_ValidData_ReturnsUpdatedUser() {
        Long userId = createTestUser();
        UserUpdateDto updateDto = new UserUpdateDto(
                "updatedLogin",
                "Updated Name",
                "updated@example.com"
        );

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(updateDto)
                .put("/api/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", equalTo(updateDto.getName()))
                .body("login", equalTo(updateDto.getLogin()))
                .body("email", equalTo(updateDto.getEmail()));
    }

    @Test
    void deleteUser_ExistingUser_Returns204() {
        Long userId = createTestUser();

        given()
                .header("Authorization", "Bearer " + token)
                .delete("/api/users/" + userId)
                .then()
                .statusCode(204);

        given()
                .header("Authorization", "Bearer " + token)
                .get("/api/users/" + userId)
                .then()
                .statusCode(404);
    }

    @Test
    void registerUser_InvalidData_Returns400() {
        given()
                .contentType(ContentType.JSON)
                .body("""
            {
                "login": "",
                "name": "",
                "email": "invalid-email",
                "password": "short"
            }
            """)
                .post("/api/auth/register")
                .then()
                .log().all()
                .statusCode(400)
                .body("errors", hasSize(greaterThan(0)));
    }

    private Long createTestUser() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String email = "test" + timestamp + "@example.com";
        String login = "testuser" + timestamp;

        return given()
                .contentType(ContentType.JSON)
                .body(String.format("""
                {
                    "login": "%s",
                    "name": "Test User",
                    "email": "%s",
                    "password": "SecurePass123"
                }
                """, login, email))
                .post("/api/auth/register")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getLong("id");
    }
}
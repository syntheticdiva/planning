package com.java.planning.controller;

import com.java.planning.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AuthControllerTest extends BaseTest {

    @Test
    void login_InvalidCredentials_Returns401() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                            "email": "user@example.com",
                            "password": "user123"
                        }
                        """)
                .post("/api/auth/login")
                .then()
                .statusCode(401);
    }
}
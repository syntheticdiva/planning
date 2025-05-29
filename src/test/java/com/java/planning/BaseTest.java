package com.java.planning;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class BaseTest {

    @LocalServerPort
    protected int port;

    protected String token;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";

        registerAndLoginUser();
    }

    private void registerAndLoginUser() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String email = "test" + timestamp + "@example.com";
        String login = "testuser" + timestamp;
        String password = "SecurePass123";

        RestAssured.given()
                .contentType("application/json")
                .body(String.format("""
                {
                    "login": "%s",
                    "name": "Test User",
                    "email": "%s",
                    "password": "%s"
                }
                """, login, email, password))
                .post("/api/auth/register")
                .then()
                .statusCode(201);

        this.token = RestAssured.given()
                .contentType("application/json")
                .body(String.format("""
                {
                    "email": "%s",
                    "password": "%s"
                }
                """, email, password))
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}

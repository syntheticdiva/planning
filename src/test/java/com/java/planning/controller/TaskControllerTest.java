package com.java.planning.controller;

import com.java.planning.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;

class TaskControllerTest extends BaseTest {
    @Test
    void createTask_ValidRequest_Returns201() {
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("""
            {
                "title": "New Task",
                "description": "Task Description"
            }
            """)
                .post("/api/tasks")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", notNullValue())
                .body("title", equalTo("New Task"));
    }
    @Test
    void getTaskById_ExistingId_Returns200() {
        Long taskId = createTestTask();

        given()
                .header("Authorization", "Bearer " + token)
                .get("/api/tasks/" + taskId)
                .then()
                .statusCode(200)
                .body("id", equalTo(taskId.intValue()));
    }
    private Long createTestTask() {
        return given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{\"title\":\"Test Task\"}")
                .post("/api/tasks")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath()
                .getLong("id");
    }
    @Test
    void getAllTasks_ReturnsListOfTasks() {
        createTestTask();

        given()
                .header("Authorization", "Bearer " + token)
                .get("/api/tasks")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    void updateTask_ValidRequest_ReturnsUpdatedTask() {
        Long taskId = createTestTask();

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("""
            {
                "title": "Updated Task",
                "description": "Updated Description"
            }
            """)
                .put("/api/tasks/" + taskId)
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Task"))
                .body("description", equalTo("Updated Description"));
    }

    @Test
    void deleteTask_ExistingId_Returns204() {
        Long taskId = createTestTask();

        given()
                .header("Authorization", "Bearer " + token)
                .delete("/api/tasks/" + taskId)
                .then()
                .statusCode(204);

        given()
                .header("Authorization", "Bearer " + token)
                .get("/api/tasks/" + taskId)
                .then()
                .statusCode(404);
    }


    @Test
    void getTaskById_NonExistingId_Returns404() {
        given()
                .header("Authorization", "Bearer " + token)
                .get("/api/tasks/9999")
                .then()
                .statusCode(404);
    }

    @Test
    void updateTask_NonExistingId_Returns404() {
        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body("{\"title\":\"Updated Task\"}")
                .put("/api/tasks/9999")
                .then()
                .statusCode(404);
    }
}
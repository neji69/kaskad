package com.kaskad.testing.api;

import com.kaskad.testing.BaseTestClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.kaskad.testing.reporting.AllureReportingUtils.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("API Тестирование")
@Story("Пример API теста с использованием REST Assured")
public class ApiTestExample extends BaseTestClass {

    private final ThreadSafeApiClient apiClient;

    public ApiTestExample(ThreadSafeApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @AfterEach
    void tearDown() {
        apiClient.clearThreadLocal();
    }

    @Test
    public void testGetEndpointReturnsSuccess() {
        step("Выполнение GET запроса к эндпоинту");
        Response response = given()
                .spec(apiClient.getRequestSpec())
                .when()
                .get("/api/test")
                .then()
                .extract().response();

        step("Проверка статуса ответа");
        assertEquals(200, response.getStatusCode(), "Статус ответа должен быть 200");

        step("Проверка содержимого ответа");
        String responseBody = response.asString();
        attachJsonResponse("Ответ от API", responseBody);

        // Пример проверки содержимого
        given()
                .spec(apiClient.getRequestSpec())
                .when()
                .get("/api/test")
                .then()
                .statusCode(200)
                .body("status", equalTo("success"));
    }
}
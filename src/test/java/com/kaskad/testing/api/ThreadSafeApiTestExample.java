package com.kaskad.testing.api;

import com.kaskad.testing.BaseTestClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.kaskad.testing.reporting.AllureReportingUtils.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("API Тестирование")
@Story("Пример многопоточного API теста с использованием ThreadLocal")
public class ThreadSafeApiTestExample extends BaseTestClass {

    private final ThreadSafeApiClient apiClient;

    public ThreadSafeApiTestExample(ThreadSafeApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @AfterEach
    void tearDown() {
        apiClient.clearThreadLocal();
    }

    @Test
    public void testConcurrentApiCalls() {
        step("Выполнение параллельных вызовов API");
        
        // Выполнение нескольких вызовов API с использованием одного и того же клиента
        // благодаря ThreadLocal каждый поток будет использовать свою спецификацию
        Response response1 = given()
                .spec(apiClient.getRequestSpec())
                .when()
                .get("/api/users")
                .then()
                .extract().response();

        step("Проверка первого ответа");
        assertEquals(200, response1.getStatusCode());

        Response response2 = given()
                .spec(apiClient.getRequestSpec())
                .when()
                .get("/api/products")
                .then()
                .extract().response();

        step("Проверка второго ответа");
        assertEquals(200, response2.getStatusCode());

        // Пример проверки содержимого
        given()
                .spec(apiClient.getRequestSpec())
                .when()
                .get("/api/status")
                .then()
                .statusCode(200)
                .body("status", equalTo("ok"));
    }
}
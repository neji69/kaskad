package com.kaskad.testing;

import com.kaskad.testing.api.ThreadSafeApiClient;
import com.kaskad.testing.db.DatabaseService;
import com.kaskad.testing.messaging.KafkaService;
import com.kaskad.testing.ui.PageService;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.kaskad.testing.reporting.AllureReportingUtils.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Комплексное тестирование")
@Story("Интеграционный тест, объединяющий все компоненты")
public class IntegrationTestExample extends BaseTestClass {

    private final ThreadSafeApiClient apiClient;
    private final DatabaseService databaseService;
    private final KafkaService kafkaService;
    private final PageService pageService;

    public IntegrationTestExample(ThreadSafeApiClient apiClient,
                                 DatabaseService databaseService,
                                 KafkaService kafkaService,
                                 PageService pageService) {
        this.apiClient = apiClient;
        this.databaseService = databaseService;
        this.kafkaService = kafkaService;
        this.pageService = pageService;
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    public void testEndToEndScenario() throws Exception {
        step("Начало комплексного теста");

        // Проверка API
        step("Проверка API доступности");
        // Здесь можно добавить вызов API и проверку результата

        // Проверка базы данных
        step("Проверка состояния базы данных");
        try (var connection = databaseService.getConnection()) {
            assertTrue(connection.isValid(5), "Соединение с базой данных должно быть активным");
        }

        // Проверка Kafka
        step("Проверка отправки сообщения через Kafka");
        var future = kafkaService.sendMessage("integration-test-topic", "Integration test message");
        assertTrue(future.isDone(), "Сообщение должно быть отправлено");

        // Проверка UI (при необходимости)
        step("Проверка UI компонента");
        var page = pageService.createPage();
        // Здесь можно добавить проверки UI
        pageService.closePage(page);

        step("Комплексный тест завершен успешно");
    }
}
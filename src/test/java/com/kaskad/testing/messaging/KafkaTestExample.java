package com.kaskad.testing.messaging;

import com.kaskad.testing.BaseTestClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static com.kaskad.testing.reporting.AllureReportingUtils.step;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Feature("Тестирование сообщений через Kafka")
@Story("Пример теста для проверки отправки и получения сообщений через Kafka")
public class KafkaTestExample extends BaseTestClass {

    private final KafkaService kafkaService;

    public KafkaTestExample(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @Test
    public void testSendMessageToTopic() {
        step("Подготовка сообщения для отправки");
        String testMessage = "Тестовое сообщение";
        String topic = "test-topic";

        step("Отправка сообщения в топик Kafka");
        var future = kafkaService.sendMessage(topic, testMessage);

        step("Ожидание подтверждения отправки сообщения");
        await().atMost(10, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    assertNotNull(future.get(), "Сообщение должно быть успешно отправлено");
                });
    }
}
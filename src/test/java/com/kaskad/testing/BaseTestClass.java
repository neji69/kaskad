package com.kaskad.testing;

import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest
@ExtendWith(AllureJunit5.class)
public abstract class BaseTestClass {

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        // Здесь можно настроить динамические свойства для тестов
        // например, registry.add("spring.datasource.url", () -> testContainer::getJdbcUrl);
    }

    @BeforeEach
    void setUp() {
        // Общая настройка для всех тестов
    }
}
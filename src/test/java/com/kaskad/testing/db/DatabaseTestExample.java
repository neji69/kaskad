package com.kaskad.testing.db;

import com.kaskad.testing.BaseTestClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.kaskad.testing.reporting.AllureReportingUtils.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Тестирование базы данных")
@Story("Пример теста для проверки состояния базы данных")
public class DatabaseTestExample extends BaseTestClass {

    private final DatabaseService databaseService;

    public DatabaseTestExample(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Test
    public void testDataExistsInTable() throws Exception {
        step("Подключение к базе данных");
        try (Connection connection = databaseService.getConnection()) {
            
            step("Выполнение SQL запроса");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM some_table");
            
            step("Проверка результатов запроса");
            resultSet.next();
            int count = resultSet.getInt(1);
            assertTrue(count > 0, "В таблице должны быть данные");
        }
    }
}
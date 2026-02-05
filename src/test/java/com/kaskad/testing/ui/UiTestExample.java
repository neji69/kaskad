package com.kaskad.testing.ui;

import com.kaskad.testing.BaseTestClass;
import com.microsoft.playwright.Page;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static com.kaskad.testing.reporting.AllureReportingUtils.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("UI Тестирование")
@Story("Пример UI теста с использованием Playwright")
public class UiTestExample extends BaseTestClass {

    private final PageService pageService;

    public UiTestExample(PageService pageService) {
        this.pageService = pageService;
    }

    @Test
    public void testHomePageLoads() {
        step("Открытие главной страницы");
        Page page = pageService.createPage();
        
        step("Переход на главную страницу");
        page.navigate(System.getProperty("test.base.url", "http://localhost:8080"));
        
        step("Проверка заголовка страницы");
        String title = page.title();
        assertTrue(title.contains("Home"), "Заголовок страницы не содержит 'Home'");
        
        step("Закрытие страницы");
        pageService.closePage(page);
    }
}
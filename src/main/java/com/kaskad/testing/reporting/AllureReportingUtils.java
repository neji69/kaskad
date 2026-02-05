package com.kaskad.testing.reporting;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class AllureReportingUtils {

    @Step("Выполняется шаг: {stepName}")
    public static void step(String stepName) {
        // Этот метод используется для документирования шагов в отчете Allure
    }

    @Attachment(value = "Скриншот страницы", type = "image/png")
    public static byte[] attachScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @Attachment(value = "Тело ответа: {attachmentName}", type = "application/json")
    public static String attachJsonResponse(String attachmentName, String response) {
        return response;
    }

    @Attachment(value = "Текстовый лог: {attachmentName}")
    public static String attachTextLog(String attachmentName, String log) {
        return log;
    }
}
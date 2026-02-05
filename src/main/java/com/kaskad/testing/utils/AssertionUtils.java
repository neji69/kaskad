package com.kaskad.testing.utils;

import static org.assertj.core.api.Assertions.*;

public class AssertionUtils {

    /**
     * Метод для демонстрации использования AssertJ в проекте
     */
    public static void demonstrateAssertions() {
        // Примеры использования AssertJ
        assertThat(2).isGreaterThan(1);
        assertThat("test").startsWith("te").endsWith("st");
        assertThat(new int[]{1, 2, 3}).hasSize(3).contains(2);
    }
}
package com.kaskad.testing.config;

import com.kaskad.testing.api.ThreadSafeApiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiClientConfig {

    // Читаем URL из конфига, если его нет — берем дефолт
    @Value("${api.base.url:http://localhost:8080}")
    private String apiBaseUrl;

    @Bean
    public ThreadSafeApiClient threadSafeApiClient() {
        return new ThreadSafeApiClient(apiBaseUrl);
    }
}

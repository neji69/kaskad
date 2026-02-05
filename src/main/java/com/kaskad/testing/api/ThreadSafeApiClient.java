package com.kaskad.testing.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ThreadSafeApiClient {

    private final String baseUrl;
    private final ThreadLocal<RequestSpecification> threadLocalSpec = new ThreadLocal<>();

    public RequestSpecification getRequestSpec() {
        RequestSpecification spec = threadLocalSpec.get();
        if (spec == null) {
            spec = new RequestSpecBuilder()
                    .setBaseUri(baseUrl)
                    .setContentType(ContentType.JSON)
                    // Тут можно добавить дефолтные фильтры, например Allure
                    .build();
            threadLocalSpec.set(spec);
        }
        return spec;
    }

    public void clear() {
        threadLocalSpec.remove();
    }
}
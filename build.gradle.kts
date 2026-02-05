plugins {
    java
    id("org.springframework.boot") version "4.0.2"
    id("io.spring.dependency-management") version "1.1.7"
    id("io.qameta.allure") version "3.0.1"
    id("io.freefair.lombok") version "8.12.1"
}

group = "com.kaskad"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starter
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-test")

    // Data JPA для работы с БД через репозитории
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Kafka for messaging tests
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.kafka:spring-kafka-test")

    // Playwright for UI testing
    implementation("com.microsoft.playwright:playwright:1.58.0")

    // REST Assured for API testing
    implementation(platform("io.rest-assured:rest-assured-bom:6.0.0"))
    implementation("io.rest-assured:rest-assured")
    implementation("io.rest-assured:spring-mock-mvc")
    implementation("io.rest-assured:json-path")
    implementation("io.rest-assured:xml-path")

    // Allure for reporting
    implementation(platform("io.qameta.allure:allure-bom:2.32.0"))
    implementation("io.qameta.allure:allure-junit5")
    implementation("io.qameta.allure:allure-rest-assured")
    implementation("io.qameta.allure:allure-assertj")
    implementation("io.qameta.allure:allure-awaitility")

    // PostgreSQL driver for database testing
    runtimeOnly("org.postgresql:postgresql")
}

tasks.withType<Test> {
    systemProperty("file.encoding", "UTF-8")
    // Чтобы в консоли Gradle тоже был нормальный русский:
    testLogging {
        showStandardStreams = true
    }
    useJUnitPlatform()
    systemProperty("allure.results.directory", layout.buildDirectory.dir("allure-results"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

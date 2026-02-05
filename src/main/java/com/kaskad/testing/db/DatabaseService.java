package com.kaskad.testing.db;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Service
public class DatabaseService {

    private final String dbUrl;
    private final String username;
    private final String password;

    public DatabaseService() {
        this.dbUrl = System.getenv("TEST_DB_URL");
        this.username = System.getenv("TEST_DB_USERNAME");
        this.password = System.getenv("TEST_DB_PASSWORD");
    }

    public Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);

        return DriverManager.getConnection(dbUrl, props);
    }
}
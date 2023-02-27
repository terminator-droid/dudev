package com.dudev.basetest;

import com.dudev.util.ConnectionManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

public abstract class IntegrationTestBase {

    private static final String CLEAR_SQL = """
            DELETE FROM brands;
            DELETE FROM categories;
            DELETE FROM change_types;
            DELETE FROM guitars;
            DELETE FROM products;
            DELETE FROM pedals;
            DELETE FROM offers;
            DELETE FROM offers_products;
            DELETE FROM users;
            DELETE FROM users_liked_products;
            """;
    private static final String DDL_FILE_PATH = "src/main/resources/database_DDL.sql";
    private static String CREATE_TABLES;

    static {
        initCreation();
    }

    private static void initCreation() {
        Path path = Path.of(DDL_FILE_PATH);
        try {
            CREATE_TABLES = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeAll
    static void prepareDatabase() throws SQLException {
        try (var connection = ConnectionManager.get();
             var statement = connection.createStatement()) {
            statement.execute(CREATE_TABLES);
        }
    }

    @BeforeEach
    void cleanData() throws SQLException {
        try (var connection = ConnectionManager.get();
             var statement = connection.createStatement()) {
            statement.execute(CLEAR_SQL);
        }
    }

}

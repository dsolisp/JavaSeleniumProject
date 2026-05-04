package com.automation.backend;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DB seeding for hermetic backend tests.
 *
 * Must accept a Connection so tests can supply an isolated DB instance.
 */
public final class DbSeeder {
    private DbSeeder() {}

    public static void seed(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            stmt.executeUpdate("DROP TABLE IF EXISTS products");

            stmt.executeUpdate("CREATE TABLE users (id INT, username TEXT, role TEXT)");
            stmt.executeUpdate("INSERT INTO users (id, username, role) VALUES (1, 'standard_user', 'customer')");
            stmt.executeUpdate("INSERT INTO users (id, username, role) VALUES (2, 'admin_user', 'admin')");

            stmt.executeUpdate("CREATE TABLE products (id INT, name TEXT, price REAL)");
            stmt.executeUpdate("INSERT INTO products (id, name, price) VALUES (1, 'Sauce Labs Backpack', 29.99)");
        }
    }
}


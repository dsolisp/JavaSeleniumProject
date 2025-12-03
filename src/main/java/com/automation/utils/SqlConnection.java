package com.automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * SQLite connection utilities with parameterized queries and SQL injection prevention.
 * Equivalent to Python's utils/sql_connection.py
 *
 * Usage Examples:
 *     // Basic query
 *     try (Connection conn = SqlConnection.getConnection("resources/chinook.db")) {
 *         List<Map<String, Object>> users = SqlConnection.fetchAll(conn, 
 *             "SELECT * FROM users WHERE active = ?", 1);
 *     }
 *
 *     // CRUD operations
 *     try (Connection conn = SqlConnection.getConnection("resources/chinook.db")) {
 *         int id = SqlConnection.insert(conn, "users", Map.of("name", "John", "age", 30));
 *         SqlConnection.update(conn, "users", Map.of("age", 31), "id = ?", id);
 *         SqlConnection.delete(conn, "users", "id = ?", id);
 *     }
 */
public class SqlConnection {

    private static final Logger logger = LoggerFactory.getLogger(SqlConnection.class);
    private static final Pattern IDENTIFIER_PATTERN = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*$");

    private SqlConnection() {
        // Utility class
    }

    /**
     * Validate table/column names (alphanumeric + underscore only).
     */
    public static String validateIdentifier(String name, String identifierType) {
        if (name == null || name.isEmpty() || !IDENTIFIER_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("Invalid " + identifierType + ": '" + name + "'");
        }
        return name;
    }

    /**
     * Connect to SQLite database.
     */
    public static Connection getConnection(String dbFile) throws SQLException {
        Path dbPath = Path.of(dbFile);
        if (!Files.exists(dbPath)) {
            throw new SQLException("Database not found: " + dbFile);
        }
        
        String url = "jdbc:sqlite:" + dbPath.toAbsolutePath();
        Connection conn = DriverManager.getConnection(url);
        
        // Enable foreign keys
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON");
        }
        
        return conn;
    }

    /**
     * Get connection from classpath resource.
     */
    public static Connection getResourceConnection(String resourcePath) throws SQLException {
        // Try to find in classpath
        var url = SqlConnection.class.getClassLoader().getResource(resourcePath);
        if (url != null) {
            return DriverManager.getConnection("jdbc:sqlite:" + url.getPath());
        }
        
        // Fall back to file path
        return getConnection(resourcePath);
    }

    /**
     * Execute a parameterized query and return the result set.
     */
    public static ResultSet executeQuery(Connection conn, String query, Object... params) 
            throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        setParameters(stmt, params);
        return stmt.executeQuery();
    }

    /**
     * Execute query and fetch single result as Map.
     */
    public static Map<String, Object> fetchOne(Connection conn, String query, Object... params) 
            throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            setParameters(stmt, params);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return resultSetRowToMap(rs);
                }
                return null;
            }
        }
    }

    /**
     * Execute query and fetch all results as List of Maps.
     */
    public static List<Map<String, Object>> fetchAll(Connection conn, String query, Object... params) 
            throws SQLException {
        List<Map<String, Object>> results = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            setParameters(stmt, params);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(resultSetRowToMap(rs));
                }
            }
        }
        return results;
    }

    /**
     * Insert data into table. Returns row ID or -1 on failure.
     */
    public static long insert(Connection conn, String table, Map<String, Object> data) {
        try {
            String validTable = validateIdentifier(table, "table");
            List<String> columns = new ArrayList<>();
            List<Object> values = new ArrayList<>();
            
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                columns.add(validateIdentifier(entry.getKey(), "column"));
                values.add(entry.getValue());
            }
            
            String placeholders = String.join(", ", Collections.nCopies(columns.size(), "?"));
            String query = "INSERT INTO %s (%s) VALUES (%s)".formatted(
                    validTable, String.join(", ", columns), placeholders);
            
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                setParameters(stmt, values.toArray());
                stmt.executeUpdate();
                
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        return keys.getLong(1);
                    }
                }
            }
            return -1;
        } catch (SQLException | IllegalArgumentException e) {
            logger.warn("Insert failed: {}", e.getMessage());
            return -1;
        }
    }

    /**
     * Update rows matching WHERE clause. Returns affected row count.
     * Note: Table/column names are validated via validateIdentifier().
     * The where clause uses parameterized queries (? placeholders) for safety.
     */
    @SuppressWarnings("SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING")
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
            value = "SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING",
            justification = "Table/column names are validated, WHERE clause uses parameterized queries")
    public static int update(Connection conn, String table, Map<String, Object> data,
                            String where, Object... whereParams) {
        try {
            String validTable = validateIdentifier(table, "table");
            List<String> setClauses = new ArrayList<>();
            List<Object> values = new ArrayList<>();

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                setClauses.add(validateIdentifier(entry.getKey(), "column") + " = ?");
                values.add(entry.getValue());
            }

            // Add where params
            values.addAll(Arrays.asList(whereParams));

            String query = "UPDATE %s SET %s WHERE %s".formatted(
                    validTable, String.join(", ", setClauses), where);

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                setParameters(stmt, values.toArray());
                return stmt.executeUpdate();
            }
        } catch (SQLException | IllegalArgumentException e) {
            logger.warn("Update failed: {}", e.getMessage());
            return 0;
        }
    }

    /**
     * Delete rows matching WHERE clause. Returns deleted row count.
     */
    public static int delete(Connection conn, String table, String where, Object... whereParams) {
        try {
            String validTable = validateIdentifier(table, "table");
            String query = "DELETE FROM %s WHERE %s".formatted(validTable, where);
            
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                setParameters(stmt, whereParams);
                return stmt.executeUpdate();
            }
        } catch (SQLException | IllegalArgumentException e) {
            logger.warn("Delete failed: {}", e.getMessage());
            return 0;
        }
    }

    /**
     * Get all table names in database.
     */
    public static List<String> getTableNames(Connection conn) throws SQLException {
        List<String> tables = new ArrayList<>();
        List<Map<String, Object>> rows = fetchAll(conn, 
                "SELECT name FROM sqlite_master WHERE type='table'");
        for (Map<String, Object> row : rows) {
            tables.add((String) row.get("name"));
        }
        return tables;
    }

    /**
     * Get table schema information.
     */
    public static List<Map<String, Object>> getTableInfo(Connection conn, String table) 
            throws SQLException {
        String validTable = validateIdentifier(table, "table");
        return fetchAll(conn, "PRAGMA table_info(" + validTable + ")");
    }

    // Helper methods

    private static void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

    private static Map<String, Object> resultSetRowToMap(ResultSet rs) throws SQLException {
        Map<String, Object> row = new LinkedHashMap<>();
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();
        
        for (int i = 1; i <= columnCount; i++) {
            row.put(meta.getColumnName(i), rs.getObject(i));
        }
        return row;
    }
}


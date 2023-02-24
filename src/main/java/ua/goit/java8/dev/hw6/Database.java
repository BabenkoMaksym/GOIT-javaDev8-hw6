package ua.goit.java8.dev.hw6;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final Logger LOG = Logger.getLogger(Database.class);

    private static Connection conn;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/goit_java8_hw6";
    private static final String DB_USER_NAME = "maksbbn";
    private static final String DB_USER_PASSWORD = "qwerty";

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            try {
                conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD);
            } catch (SQLException e) {
                LOG.error("Connection crate error" + e);
                e.printStackTrace();
            }
            LOG.info("Connection created");
        }
        return conn;
    }
}

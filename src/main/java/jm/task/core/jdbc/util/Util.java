package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL =
            "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        return conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void close() throws SQLException {
        conn.close();
    }
}

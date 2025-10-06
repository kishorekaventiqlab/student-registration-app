package com.gana.student.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String host = System.getenv().getOrDefault("DB_HOST", "mysql-db");
        String port = System.getenv().getOrDefault("DB_PORT", "3306");
        String db = System.getenv().getOrDefault("DB_NAME", "studentdb");
        String user = System.getenv().getOrDefault("DB_USER", "appuser");
        String pass = System.getenv().getOrDefault("DB_PASSWORD", "apppassword");
        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", host, port, db);
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}

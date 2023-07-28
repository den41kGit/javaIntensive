package org.denischernous.homeworks.lesson3.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс для подключения к БД
 */
public class DbConnector {
    private final static String DB_URL = "jdbc:postgresql://localhost:5432/bookstores";
    private final static String DB_USER = "postgres";
    private final static String DB_PASSWORD = "123";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

}

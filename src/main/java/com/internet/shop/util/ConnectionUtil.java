package com.internet.shop.util;

import com.internet.shop.exceptions.DataBaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException message) {
            throw new RuntimeException("Can't find mySQL driver", message);
        }
    }

    public static Connection getConnection() {
        Properties dbProperties = new Properties();
        dbProperties.put("user", "root");
        dbProperties.put("password", "javapractice");
        dbProperties.put("serverTimezone", "UTC");
        String url = "jdbc:mysql://localhost:3306/internet_shop";
        try {
            return DriverManager.getConnection(url, dbProperties);
        } catch (SQLException message) {
            throw new DataBaseException("Can't establish the connection to DB", message);
        }
    }
}

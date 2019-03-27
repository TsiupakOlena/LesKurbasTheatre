package com.leskurbas.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String PROPERTIES_FILE = "database.properties";
    private static final String JDBC_DRIVER = getPropertyValue("driver");
    private static final String DB_URL = getPropertyValue("dburl");
    private static final String USER = getPropertyValue("user");
    private static final String PASS = getPropertyValue("password");

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(false);
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    private static String getPropertyValue(String property) {
        InputStream inputStream = null;
        String result = null;
        Properties properties = new Properties();

        try {
            inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(PROPERTIES_FILE);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '"
                        + PROPERTIES_FILE + "' not found in the classpath");
            }

            result =  properties.getProperty(property);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }

            return result;
        }
    }
}
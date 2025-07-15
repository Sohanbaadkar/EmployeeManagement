package com.ems;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBconfig {
    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;

    static {
        Properties props = new Properties();
        try {
            // Use the **correct relative path**
            FileInputStream fis = new FileInputStream("config.properties");
            props.load(fis);

            dbUrl = props.getProperty("db.url");
            dbUsername = props.getProperty("db.username");
            dbPassword = props.getProperty("db.password");

            // Debug print
            System.out.println("Loaded properties: " + dbUrl + ", " + dbUsername + ", " + dbPassword);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDbUrl() {
        return dbUrl;
    }

    public static String getDbUsername() {
        return dbUsername;
    }

    public static String getDbPassword() {
        return dbPassword;
    }
}

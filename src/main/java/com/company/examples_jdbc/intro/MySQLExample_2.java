package com.company.examples_jdbc.intro;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLExample_2 {
    private static final String JDBC_URL
            = "jdbc:mysql://127.0.0.1:3306/quiz_db?user=root&password=root&useSSL=true";

    public static void main(String[] args) throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        try (Connection conn = driver.connect(JDBC_URL, new Properties())) {
            System.out.println("conn = " + conn);
        }
    }
}

package com.company.examples_jdbc.intro;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLExample_3a {
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/quiz_db?user=root&password=root";

    public static void main(String[] args) throws SQLException {
//        System.out.println(DriverManager.getDriver("ABC"));
        System.out.println(DriverManager.getDriver(JDBC_URL));
        System.out.println(DriverManager.getConnection(JDBC_URL));
    }
}

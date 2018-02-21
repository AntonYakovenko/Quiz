package com.company.examples_jdbc.intro;

import java.sql.*;

public class MySQLExample_5 {
    private static final String JDBC_URL
            = "jdbc:mysql://127.0.0.1:3306/quiz_db?user=root&password=root";

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT id, login, name, password FROM users");
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                int id = rs.getInt("id");
//                int id = rs.getInt(1);
                String login = rs.getString("login");
                String name = rs.getString("name");
                String password = rs.getString("password");
                System.out.printf("id=%d, login=%s, name=%s, password=%s\n", id, login, name, password);
            }
        }
    }
}

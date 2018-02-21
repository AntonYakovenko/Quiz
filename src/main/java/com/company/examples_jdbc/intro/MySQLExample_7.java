package com.company.examples_jdbc.intro;

import java.sql.*;

public class MySQLExample_7 {
    private static final String JDBC_URL
            = "jdbc:mysql://127.0.0.1:3306/quiz_db?user=root&password=root";

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            conn.setHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT);
            conn.setReadOnly(true);
            Statement stmt = conn.createStatement();
            stmt.setFetchDirection(ResultSet.FETCH_FORWARD);
            ResultSet rs = stmt.executeQuery("SELECT id FROM users");
            while (rs.next()) {
                System.out.println(rs.getInt("id"));
            }
            conn.commit();
        }
    }
}

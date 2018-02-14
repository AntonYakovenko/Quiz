package com.company.quiz.dao.impl.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLExample_6 {
    private static final String JDBC_URL
            = "jdbc:mysql://127.0.0.1:3306/quiz_db?user=root&password=root";

    public static void main(String[] args) throws SQLException {
        long startTime = System.nanoTime();
        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            long connectionTime = System.nanoTime();
            System.out.println("conn = " + (double) (connectionTime - startTime) / 1_000_000 + " ms");
            conn.setAutoCommit(false);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (id, login, name, password) VALUES (?, ?, ?, ?)");
            for (int k = 0; k < 5; k++) {
                stmt.setInt(1, (int) System.nanoTime());
                stmt.setString(2, "" + System.nanoTime());
                stmt.setString(3, "" + System.nanoTime());
                stmt.setString(4, "" + System.nanoTime());
                long rowsAffectingStartTime = System.nanoTime();
                int howMuchRowsAffected = stmt.executeUpdate();
                long rowsAffectingEndTime = System.nanoTime();
                System.out.println("sql = " + (double) (rowsAffectingEndTime - rowsAffectingStartTime) / 1_000_000 + " ms");
                System.out.println(howMuchRowsAffected + " rows affected");
            }
            long startApplyTime = System.nanoTime();
            conn.rollback();
//            conn.commit();
            long endApplyTime = System.nanoTime();
            System.out.println("apply = " + (double) (endApplyTime - startApplyTime) / 1_000_000 + " ms");
        }
    }
}

package com.company.quiz.dao.impl.test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class MySQLExample_3 {
    public static void main(String[] args) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            System.out.println("driver = " + driver);
        }
    }
}

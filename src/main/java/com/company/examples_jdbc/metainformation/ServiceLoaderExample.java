package com.company.examples_jdbc.metainformation;

import java.sql.Driver;
import java.util.ServiceLoader;

public class ServiceLoaderExample {
    public static void main(String[] args) {
        System.out.println("---javax.sql.Driver---");
        ServiceLoader<Driver> driverLoader = ServiceLoader.load(Driver.class);
        for (Driver driver : driverLoader) {
            System.out.println(driver.getClass().getName());
        }
    }
}

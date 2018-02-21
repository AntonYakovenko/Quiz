package com.company.inject.spring_example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpringExample {
    public static void main(String[] args) throws IOException {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("/appContext.xml");
        User user0 = (User) appCtx.getBean("user");
        User user1 = (User) appCtx.getBean("user");
        System.out.println(user0 == user1);

//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        while (true) {
//            System.out.println("Enter NickName: ");
//            String line = bufferedReader.readLine();
//            if (line.matches("\\s")) {
//                System.out.println("Name contains digits");
//            } else {
//                System.out.println("Name does not contains digits");
//            }
//        }
    }
}

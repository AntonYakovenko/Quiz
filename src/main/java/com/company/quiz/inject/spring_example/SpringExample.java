package com.company.quiz.inject.spring_example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class SpringExample {
    public static void main(String[] args) {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("/appContext.xml");
        User user0 = (User) appCtx.getBean("user");
        User user1 = (User) appCtx.getBean("user");
        System.out.println(user0 == user1);
        System.out.println(1 / 1);
    }
}

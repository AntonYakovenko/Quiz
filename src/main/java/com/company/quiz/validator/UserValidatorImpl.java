package com.company.quiz.validator;

import com.company.quiz.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserValidatorImpl implements UserValidator {
    @Override
    public Map<String, String> validate(User user) {
        Map<String, String> errorMap = new HashMap<>();
        validateLogin(user.getLogin(), errorMap);
        validateName(user.getName(), errorMap);
        validatePassword(user.getPassword(), errorMap);
        validateEmail(user.getEmail(), errorMap);
        return errorMap;
    }

    private void validateLogin(String login, Map<String, String> errorMap) {
        if (login == null) {
            errorMap.put("login", "login == null");
        } else if (login.length() < 3) {
            errorMap.put("login", "login.length() < 3");
        } else if (login.length() > 10) {
            errorMap.put("login", "login.length() > 10");
        }
    }

    private void validateName(String name, Map<String, String> errorMap) {
        if (name == null) {
            errorMap.put("name", "name == null");
        } else if (name.length() < 3) {
            errorMap.put("name", "name.length() < 3");
        } else if (name.length() > 10) {
            errorMap.put("name", "name.length() > 10");
        }
    }

    private void validatePassword(String password, Map<String, String> errorMap) {
        if (password == null) {
            errorMap.put("password", "password == null");
        } else if (password.length() < 3) {
            errorMap.put("password", "password.length() < 3");
        } else if (password.length() > 10) {
            errorMap.put("password", "password.length() > 10");
        }

    }

    private void validateEmail(String email, Map<String, String> errorMap) {
        if (email == null) {
            errorMap.put("email", "email == null");
        } else if (email.length() < 3) {
            errorMap.put("email", "email.length() < 3");
        } else if (email.length() > 20) {
            errorMap.put("email", "email.length() > 10");
        } else if (!email.contains("@")) {
            errorMap.put("email", "email doesn't contain '@'");
        }

    }
}
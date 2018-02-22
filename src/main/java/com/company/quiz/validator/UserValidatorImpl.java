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
        } else for (Character chars : login.toCharArray()) {
            if (Character.isWhitespace(chars)) {
                errorMap.put("login", "no whitespaces allowed");
            }
        }
    }

    private void validateName(String name, Map<String, String> errorMap) {
        if (name == null) {
            errorMap.put("name", "name == null");
        } else if (name.length() < 3) {
            errorMap.put("name", "name.length() < 3");
        } else if (name.length() > 10) {
            errorMap.put("name", "name.length() > 10");
        } else if (!name.matches("[a-zA-Z]+")) {
            errorMap.put("name", "name must contains only a-z or A-Z");
        } else for (Character chars : name.toCharArray()) {
            if (Character.isWhitespace(chars)) {
                errorMap.put("login", "no whitespaces allowed");
            }
        }
    }

    private void validatePassword(String password, Map<String, String> errorMap) {
        if (password == null) {
            errorMap.put("password", "password == null");
        } else if (password.length() < 3) {
            errorMap.put("password", "password.length() < 3");
        } else if (password.length() > 10) {
            errorMap.put("password", "password.length() > 10");
        } /*else for (Character chars : password.toCharArray()) {
            if (Character.isWhitespace(chars)) {
                errorMap.put("login", "no whitespaces allowed");
            }
        }*/
    }

    private void validateEmail(String email, Map<String, String> errorMap) {
        if (!email.matches("^[\\w\\d._-]+@[\\w\\d.-]+\\.[\\w\\d]{2,6}$")) {
            errorMap.put("email", "incorrect email");
        }
    }
}
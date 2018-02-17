package com.company.quiz.validator;

import com.company.quiz.entity.User;

import java.util.Map;

public interface UserValidator extends Validator<User> {
    @Override
    Map<String, String> validate(User entity);
}
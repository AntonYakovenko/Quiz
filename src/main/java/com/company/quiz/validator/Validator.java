package com.company.quiz.validator;

import java.util.Map;

public interface Validator<T> {
    Map<String, String> validate(T entity);
}
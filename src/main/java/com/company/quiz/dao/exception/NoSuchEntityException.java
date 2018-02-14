package com.company.quiz.dao.exception;

public class NoSuchEntityException extends DaoBusinessException {

    public NoSuchEntityException() {
        super();
    }

    public NoSuchEntityException(String message) {
        super(message);
    }
}

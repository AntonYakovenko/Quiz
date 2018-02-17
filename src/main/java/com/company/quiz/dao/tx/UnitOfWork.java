package com.company.quiz.dao.tx;

@FunctionalInterface
public interface UnitOfWork<T, E extends Throwable> {
    T invoke() throws E;
}

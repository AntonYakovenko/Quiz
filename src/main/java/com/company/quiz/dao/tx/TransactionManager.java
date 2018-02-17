package com.company.quiz.dao.tx;

import java.sql.SQLException;

public interface TransactionManager {

    <T, E extends Throwable> T call(UnitOfWork<T, E> unit) throws E, SQLException;
}

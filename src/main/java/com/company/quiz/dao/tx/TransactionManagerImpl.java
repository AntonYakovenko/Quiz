package com.company.quiz.dao.tx;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerImpl extends BaseDataSource implements TransactionManager, DataSource {
    private final ThreadLocal<Connection> storage = new ThreadLocal<>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public <T, E extends Throwable> T call(UnitOfWork<T, E> unit) throws E, SQLException {
        Connection conn = dataSource.getConnection();
        try {
            conn.setAutoCommit(false);
            storage.set(conn);
            T result = unit.invoke();
            conn.commit();
            return result;
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            storage.set(null);
            conn.close();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = storage.get();
        if (conn != null) {
            return conn;
        } else {
            throw new IllegalStateException("Don't call 'getConnection' not under TransactionManager!");
        }
    }
}

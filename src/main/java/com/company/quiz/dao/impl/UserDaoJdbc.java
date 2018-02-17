package com.company.quiz.dao.impl;

import com.company.quiz.dao.UserDao;
import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.dao.exception.NoSuchEntityException;
import com.company.quiz.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private DataSource dataSource;
    private static final String INSERT_USER
            = "INSERT INTO users (login, name, password, email) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_LOGIN
            = "SELECT * FROM users WHERE login = ?";
    private static final String SELECT_USER_BY_EMAIL
            = "SELECT * FROM users WHERE email = ?";

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User selectByLogin(String byLogin) throws DaoSystemException, NoSuchEntityException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SELECT_USER_BY_LOGIN);
            stmt.setString(1, byLogin);
            rs = stmt.executeQuery();
//            if (!rs.next()) {
//                throw new NoSuchEntityException("No user for login " + byLogin);
//            }
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException("Some JDBC error");
        }
    }

    @Override
    public User selectByEmail(String byEmail) throws DaoSystemException, NoSuchEntityException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SELECT_USER_BY_EMAIL);
            stmt.setString(1, byEmail);
            rs = stmt.executeQuery();
//            if (!rs.next()) {
//                throw new NoSuchEntityException("No user for email " + byEmail);
//            }
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoSystemException();
        }
    }

    @Override
    public List<User> selectAll() throws DaoSystemException, NoSuchEntityException {
        throw new UnsupportedOperationException();
    }

    @Override
    public User insertNew(User user) throws DaoSystemException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(INSERT_USER);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());
            stmt.executeUpdate();
            return selectByLogin(user.getLogin());
        } catch (SQLException | NoSuchEntityException e) {
            e.printStackTrace();
            throw new DaoSystemException("Some JDBC error");
        }
    }
}

package com.company.quiz.dao;

import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.dao.exception.NoSuchEntityException;
import com.company.quiz.entity.User;

import java.util.List;

public interface UserDao {

    User selectByLogin(String byLogin) throws DaoSystemException, NoSuchEntityException;

    User selectByEmail(String byEmail) throws DaoSystemException, NoSuchEntityException;

    List<User> selectAll() throws DaoSystemException, NoSuchEntityException;

    User insertNew(User user) throws DaoSystemException;
}

package com.company.quiz.dao;

import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.dao.exception.NoSuchEntityException;
import com.company.quiz.entity.Quiz;
import com.company.quiz.entity.Theme;

import java.util.List;

public interface QuizDao {
    Quiz selectInfoById(int byId) throws DaoSystemException;

    List<Quiz> selectAllSimpleInfo() throws DaoSystemException;

    default List<Quiz> selectByTheme(Theme theme) throws DaoSystemException {
        throw new UnsupportedOperationException();
    }
}

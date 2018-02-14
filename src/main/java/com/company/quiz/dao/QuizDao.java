package com.company.quiz.dao;

import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.dao.exception.NoSuchEntityException;
import com.company.quiz.entity.Quiz;
import com.company.quiz.entity.Theme;

import java.util.List;

public interface QuizDao {
    Quiz selectById(int id) throws DaoSystemException, NoSuchEntityException;

    List<Quiz> selectAll() throws DaoSystemException;

    List<Quiz> selectByTheme(Theme theme) throws DaoSystemException;
}

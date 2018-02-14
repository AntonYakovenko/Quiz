package com.company.quiz.dao;

import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.dao.exception.NoSuchEntityException;
import com.company.quiz.entity.Question;

import java.util.List;

public interface QuestionDao {
    Question selectById(int id) throws DaoSystemException, NoSuchEntityException;

    List<Question> selectAll() throws DaoSystemException;
}

package com.company.quiz.dao;

import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.dao.exception.NoSuchEntityException;
import com.company.quiz.entity.Question;

import java.util.List;

public interface QuestionDao {
    Question selectInfoById(int byId) throws DaoSystemException;

    List<Question> selectInfoByQuizId(int byQuizId) throws DaoSystemException;
}

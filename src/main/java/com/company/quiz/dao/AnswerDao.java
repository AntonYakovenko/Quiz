package com.company.quiz.dao;

import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.entity.Answer;

import java.util.List;

public interface AnswerDao {
    Answer selectById(int byId) throws DaoSystemException;

    List<Answer> selectByQuestionId(int byQuestionId) throws DaoSystemException;
}

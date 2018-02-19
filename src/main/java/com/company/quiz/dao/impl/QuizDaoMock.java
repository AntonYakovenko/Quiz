package com.company.quiz.dao.impl;

import com.company.quiz.dao.QuestionDao;
import com.company.quiz.dao.QuizDao;
import com.company.quiz.dao.ThemeDao;
import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.dao.exception.NoSuchEntityException;
import com.company.quiz.entity.Question;
import com.company.quiz.entity.Quiz;
import com.company.quiz.entity.Theme;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static java.util.Arrays.asList;

public class QuizDaoMock implements QuizDao {
    private Map<Integer, Quiz> memory = new HashMap<>();
    private DataSource dataSource;

    public QuizDaoMock() throws DaoSystemException, NoSuchEntityException {
        QuestionDao questions = new QuestionDaoMock();
        ThemeDao themes = new ThemeDaoMock();
        try {
            this.memory.put(1, new Quiz(1, "Loops quiz", "Some questions", asList(questions.selectInfoById(1), questions.selectInfoById(2)), null));
            this.memory.put(2, new Quiz(2, "Recursion quiz", "Some questions", asList(questions.selectInfoById(3), questions.selectInfoById(4)), null));
            this.memory.put(3, new Quiz(3, "Exceptions control-flow quiz", "Some questions", asList(questions.selectInfoById(5), questions.selectInfoById(6)), null));
            this.memory.put(4, new Quiz(4, "Exceptions checked/unchecked quiz", "Some questions", asList(questions.selectInfoById(7)), null));
        } catch (DaoSystemException e) {
            e.printStackTrace();
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Never returns null
     */
    @Override
    public Quiz selectInfoById(int id) throws DaoSystemException{
        if (!memory.containsKey(id)) {
//            throw new NoSuchEntityException("No Quiz for id == '" + id + "'");
            throw new UnsupportedOperationException();
        }
        return memory.get(id);
    }

    @Override
    public List<Quiz> selectByTheme(Theme theme) throws DaoSystemException {
        List<Quiz> result = new ArrayList<>();
        for (Quiz quiz : memory.values()) {
            if (quiz.getThemes().contains(theme)) {
                result.add(quiz);
            }
        }
        return result;
    }

    @Override
    public List<Quiz> selectAllSimpleInfo() throws DaoSystemException {
        return new ArrayList<Quiz>(memory.values());
    }
}

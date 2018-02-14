package com.company.quiz.dao.impl;

import com.company.quiz.dao.QuestionDao;
import com.company.quiz.dao.ThemeDao;
import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.dao.exception.NoSuchEntityException;
import com.company.quiz.entity.Question;

import java.util.*;

import static java.util.Arrays.asList;

public class QuestionDaoMock implements QuestionDao{
    private Map<Integer, Question> memory = new HashMap<>();

    public QuestionDaoMock() throws DaoSystemException, NoSuchEntityException {
        ThemeDao themes = new ThemeDaoMock();
        try {
            // iter
            memory.put(1, new Question(1, "iter-0", "'for' - is it a loop?", null, "'for' is a loop", null));
            memory.put(2, new Question(2, "iter-1", "'switch' - is it a loop?", null, "'switch' isn't a loop", null));
            // rec
            memory.put(3, new Question(3, "rec-0", "Can calc factorial recursively?", null, "Yes", null));
            memory.put(4, new Question(4, "rec-1", "int x = 1; // Is it recursion?", null, "No", null));
            // control-flow
            memory.put(5, new Question(5, "control-flow-0", "Can exists 'try' without 'catch'?", null,"Only in 'try-with-resources'", new ArrayList<>(Collections.singletonList(themes.selectById(1)))));
            memory.put(6, new Question(6, "control-flow-0", "Can exists 'try' without 'finally'?", null,"Yes", new ArrayList<>(Collections.singletonList(themes.selectById(1)))));
            // checked-unchecked
            memory.put(7, new Question(7, "checked-unchecked-0", "Exception checked?",null,"Exception checked", new ArrayList<>(Collections.singletonList(themes.selectById(1)))));
        } catch (DaoSystemException | NoSuchEntityException e) {
            e.printStackTrace();
        }
    }

    /**
     * Never returns null
     */
    @Override
    public Question selectById(int id) throws DaoSystemException, NoSuchEntityException {
        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("No Question for id == '" + id + "'");
        }
        return memory.get(id);
    }

    @Override
    public List<Question> selectAll() throws DaoSystemException {
        return new ArrayList<>(memory.values());
    }
}

package com.company.quiz.dao.impl;

import com.company.quiz.dao.ThemeDao;
import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.dao.exception.NoSuchEntityException;
import com.company.quiz.entity.Theme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThemeDaoMock implements ThemeDao{
    private Map<Integer, Theme> memory = new HashMap<>();

    public ThemeDaoMock() {
        this.memory.put(1, new Theme(1, "Exceptions", null, null));
    }

    @Override
    public Theme selectById(int id) throws DaoSystemException, NoSuchEntityException {
        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("No Theme for id '" + id + "'");
        }
        return memory.get(id);
    }

    @Override
    public List<Theme> selectAll() throws DaoSystemException {
        return new ArrayList<>(memory.values());
    }
}

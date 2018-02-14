package com.company.quiz.dao;

import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.dao.exception.NoSuchEntityException;
import com.company.quiz.entity.Theme;

import java.util.List;

public interface ThemeDao {
    Theme selectById(int id) throws DaoSystemException, NoSuchEntityException;

    List<Theme> selectAll() throws DaoSystemException;
}

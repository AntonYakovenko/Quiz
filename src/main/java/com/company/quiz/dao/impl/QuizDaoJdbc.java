package com.company.quiz.dao.impl;

import com.company.inject.Inject;
import com.company.quiz.dao.QuestionDao;
import com.company.quiz.dao.QuizDao;
import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.entity.Quiz;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDaoJdbc implements QuizDao {
    private DataSource dataSource;
    private static final String SELECT_QUIZ_BY_ID = "SELECT * FROM quizzes WHERE id = ?";
    private static final String SELECT_ALL_QUIZZES = "SELECT * FROM quizzes";

    @Inject("questionDao")
    private QuestionDao questionDao;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Quiz selectInfoById(int byId) throws DaoSystemException {
        Connection conn;
        PreparedStatement stmt;
        ResultSet rs;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SELECT_QUIZ_BY_ID);
            stmt.setInt(1, byId);
            rs = stmt.executeQuery();
            Quiz quiz = null;
            while(rs.next()) {
                quiz = new Quiz();
                int currentQuizId = rs.getInt("id");
                quiz.setId(currentQuizId);
                quiz.setName(rs.getString("name"));
                quiz.setDescription(rs.getString("description"));
//                quiz.setQuestions(new QuestionDaoJdbc().selectByQuizId(currentQuizId));
            }
            return quiz;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException();
        }
    }

    @Override
    public List<Quiz> selectAllSimpleInfo() throws DaoSystemException {
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_QUIZZES);
            List<Quiz> quizzesSimpleInfo = new ArrayList<>();
            while (rs.next()) {
                Quiz quiz = new Quiz();
                quiz.setId(rs.getInt("id"));
                quiz.setName(rs.getString("name"));
//                quiz.setQuestions(new QuestionDaoJdbc().selectByQuizId(currentQuizId));
                quizzesSimpleInfo.add(quiz);
            }
            return quizzesSimpleInfo;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException();
        }
    }
}

package com.company.quiz.dao.impl;

import com.company.quiz.dao.AnswerDao;
import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.entity.Answer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoJdbc implements AnswerDao {
    private DataSource dataSource;
    private static final String SELECT_ANSWERS_BY_QUESTION_ID
            = "SELECT * FROM answers WHERE questionId = ?";

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Answer selectById(int byId) throws DaoSystemException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Answer> selectByQuestionId(int byQuestionId) throws DaoSystemException {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SELECT_ANSWERS_BY_QUESTION_ID);
            stmt.setInt(1, byQuestionId);
            ResultSet rs = stmt.executeQuery();
            List<Answer> answers = new ArrayList<>();
            while (rs.next()) {
                Answer answer = new Answer();
                answer.setId(rs.getInt("id"));
                answer.setAnswer(rs.getString("answer"));
                answer.setCorrect(rs.getBoolean("correct"));
                answer.setExplanation(rs.getString("explanation"));
                answer.setQuestionId(rs.getInt("questionId"));
                answers.add(answer);
            }
            return answers;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException();
        }
    }
}

package com.company.quiz.dao.impl;

import com.company.quiz.dao.QuestionDao;
import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.entity.Question;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoJdbc implements QuestionDao {
    private DataSource dataSource;
    private static final String SELECT_QUESTION_BY_ID
            = "SELECT * FROM questions WHERE id = ?";
    private static final String SELECT_QUESTION_BY_QUIZ_ID
            = "SELECT * FROM questions WHERE quizId = ?";

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Question selectInfoById(int byId) throws DaoSystemException {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SELECT_QUESTION_BY_ID);
            stmt.setInt(1, byId);
            ResultSet rs = stmt.executeQuery();
            Question question = null;
            while (rs.next()) {
                question = new Question();
                int currentQuestionId = rs.getInt("id");
                question.setId(currentQuestionId);
                question.setName(rs.getInt("name"));
                question.setDescription(rs.getString("description"));
                question.setExplanation(rs.getString("explanation"));
                question.setQuizId(rs.getInt("quizId"));
//                question.setAnswers(new AnswerDaoJdbc().selectByQuestionId(currentQuestionId));
            }
            return question;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException();
        }
    }

    @Override
    public List<Question> selectInfoByQuizId(int byQuizId) throws DaoSystemException {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SELECT_QUESTION_BY_QUIZ_ID);
            stmt.setInt(1, byQuizId);
            ResultSet rs = stmt.executeQuery();
            List<Question> questions = new ArrayList<>();
            while (rs.next()) {
                Question question = new Question();
                int currentQuestionId = rs.getInt("id");
                question.setId(currentQuestionId);
                question.setName(rs.getInt("name"));
                question.setDescription(rs.getString("description"));
                question.setExplanation(rs.getString("explanation"));
                question.setQuizId(rs.getInt("quizId"));
//                question.setAnswers(new AnswerDaoJdbc().selectByQuestionId(currentQuestionId));
                questions.add(question);
            }
            return questions;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoSystemException();
        }
    }

    @Override
    public List<Integer> selectQuestionsIdsByQuizId(int byQuizId) throws DaoSystemException {
        List<Question> questions = selectInfoByQuizId(byQuizId);
        List<Integer> questionsIds = new ArrayList<>();
        for (Question question : questions) {
            questionsIds.add(question.getId());
        }
        return questionsIds;
    }
}

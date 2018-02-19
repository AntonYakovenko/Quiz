package com.company.quiz.controller;

import com.company.inject.DependencyInjectionServlet;
import com.company.inject.Inject;
import com.company.quiz.dao.AnswerDao;
import com.company.quiz.dao.QuestionDao;
import com.company.quiz.dao.tx.TransactionManager;
import com.company.quiz.entity.Answer;
import com.company.quiz.entity.Question;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.company.util.ClassName.getCurrentClassName;

public class QuestionController extends DependencyInjectionServlet {
    public static final String PARAM_QUESTION_ID = "id";
    public static final String ATTRIBUTE_QUESTION = "question";
    public static final String ATTRIBUTE_IS_LAST = "isLast";
    public static final String ATTRIBUTE_ANSWERS = "answers";
    public static final String ATTRIBUTE_CURRENT_QUESTION_ID = "currId";
    public static final String ATTRIBUTE_NEXT_QUESTION_ID = "nextQuestionId";
    public static final String PAGE_OK = "question.jsp";
    public static final String PAGE_ERROR = "show-error.jsp";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Inject("questionDao")
    private QuestionDao questionDao;
    @Inject("answerDao")
    private AnswerDao answerDao;
    @Inject("txManager")
    private TransactionManager txManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idQuestion = req.getParameter(PARAM_QUESTION_ID);
        try {
            final Integer id = Integer.valueOf(idQuestion);
            Question question;
            question = txManager.call(() -> {
                if (questionDao.selectInfoById(id) == null) {
                    logger.warn("No question for such id");
                }
                return questionDao.selectInfoById(id);
            });
            List<Answer> answers = txManager.call(() ->
            {
                if (answerDao.selectByQuestionId(question.getId()) == null) {
                    logger.warn("No answers for such question.id");
                }
                return answerDao.selectByQuestionId(question.getId());
            });

            // Defining first and last question in quiz
            int currentQuizId = question.getQuizId();
            List<Question> questions = txManager.call(() -> questionDao.selectInfoByQuizId(currentQuizId));
            Map<Integer, Integer> questionsIds = new HashMap<>();
            for (int i = 0; i < questions.size(); i++) {
                questionsIds.put(questions.get(i).getId(), i + 1);
            }
            List<Integer> idsInQuiz = new ArrayList<>();
            for (Question questionInQuiz : questions) {
                idsInQuiz.add(questionInQuiz.getId());
            }
            int currentQuestionId = questionsIds.get(question.getId());
            boolean isLast = currentQuestionId == questions.size();
            int nextQuestionId;
            if (isLast == false) {
                nextQuestionId = idsInQuiz.get(currentQuestionId);
            } else {
                nextQuestionId = 0;
            }

            req.setAttribute(ATTRIBUTE_QUESTION, question);
            req.setAttribute(ATTRIBUTE_IS_LAST, isLast);
            req.setAttribute(ATTRIBUTE_ANSWERS, answers);
            req.setAttribute(ATTRIBUTE_CURRENT_QUESTION_ID, currentQuestionId);
            req.setAttribute(ATTRIBUTE_NEXT_QUESTION_ID, nextQuestionId);
            logger.debug("set attribute '" + ATTRIBUTE_QUESTION + "' to " + question);
            logger.debug("set attribute '" + ATTRIBUTE_IS_LAST + "' to " + isLast);
            logger.debug("set attribute '" + ATTRIBUTE_ANSWERS + "' to " + answers);
            logger.debug("set attribute '" + ATTRIBUTE_CURRENT_QUESTION_ID + "' to " + ATTRIBUTE_CURRENT_QUESTION_ID);
            // OK
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            logger.debug("PAGE_OK: RequestDispatcher.forward(...) to " + PAGE_OK);
//                return;
        } catch (Exception e) {
            req.setAttribute("stackTrace", e.getStackTrace());
            e.printStackTrace();
            logger.debug("Some error", e);
        }
        // ERROR
//            response.sendRedirect(PAGE_ERROR);
//            logger.debug("PAGE_ERROR: RequestDispatcher.forward(...) to " + PAGE_ERROR);

    }
}

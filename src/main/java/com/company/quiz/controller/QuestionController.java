package com.company.quiz.controller;

import com.company.inject.DependencyInjectionServlet;
import com.company.inject.Inject;
import com.company.quiz.dao.QuestionDao;
import com.company.quiz.entity.Question;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.company.util.ClassName.getCurrentClassName;

public class QuestionController extends DependencyInjectionServlet {
    public static final String PARAM_QUESTION_ID = "id";
    public static final String PARAM_QUIZ_ID = "quizId";
    public static final String ATTRIBUTE_QUESTION = "question";
    public static final String ATTRIBUTE_QUIZ = "quiz";
    public static final String PAGE_OK = "question.jsp";
    public static final String PAGE_ERROR = "show-error.jsp";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Inject("questionDao")
    private QuestionDao questionDao;

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
        String idQuiz = req.getParameter(PARAM_QUIZ_ID);
        if (idQuestion != null && !idQuestion.equals("") && idQuiz != null && !idQuiz.equals("")) {
            try {
                final Integer id = Integer.valueOf(idQuestion);
                final Integer redirectId = Integer.valueOf(idQuiz);
                Question question = questionDao.selectById(id);
                req.setAttribute(ATTRIBUTE_QUESTION, question);
                req.setAttribute(ATTRIBUTE_QUIZ, redirectId);
                logger.debug("set attribute '" + ATTRIBUTE_QUESTION + "' to " + question);
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
}

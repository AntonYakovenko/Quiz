package com.company.quiz.controller;

import com.company.inject.DependencyInjectionServlet;
import com.company.inject.Inject;
import com.company.quiz.dao.QuizDao;
import com.company.quiz.dao.tx.TransactionManager;
import com.company.quiz.entity.Quiz;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.company.quiz.controller.SessionAttributes.*;
import static com.company.util.ClassName.getCurrentClassName;

public class QuizAllController extends DependencyInjectionServlet {
    public static final String ATTRIBUTE_QUIZZES_SIMPLE_INFO = "quizzesSimpleInfo";
    public static final String PAGE_OK = "quizAll.jsp";
    public static final String PAGE_ERROR = "index.jsp";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Inject("quizDao")
    private QuizDao quizDao;
    @Inject("txManager")
    private TransactionManager txManager;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            Map<Integer, Boolean> answersMap = (Map<Integer, Boolean>) session.getAttribute(ANSWERS_MAP);
            List<Integer> questionsIdsList = (List<Integer>) session.getAttribute(QUESTIONS_IDS_OF_CURRENT_QUIZ);
            Map<Integer, String> completedQuizzes = (Map<Integer, String>) session.getAttribute(COMPLETED_QUIZZES);
            logger.debug("answerMap = " + answersMap);
            logger.debug("questionsIdsList = " + questionsIdsList);

            // Count correct answers
            String result = "";
            if (questionsIdsList != null && answersMap != null) {
                int correctCounter = 0;
                for (Integer id : questionsIdsList) {
                    if (answersMap.get(id) == true) {
                        correctCounter++;
                    }
                }
                result = correctCounter + "/" + questionsIdsList.size();
            }

            if (completedQuizzes == null) {
                completedQuizzes = new HashMap<>();
            }
            Integer completedQuizId = (Integer) session.getAttribute(CURRENT_QUIZ_ID);
            session.removeAttribute(CURRENT_QUIZ_ID);
            session.removeAttribute(QUESTIONS_IDS_OF_CURRENT_QUIZ);

            List<Quiz> quizzesSimpleInfo;
            quizzesSimpleInfo = txManager.call(() -> {
                if (quizDao.selectAllSimpleInfo() == null) {
                    logger.warn("No quizzes!");
                }
                return quizDao.selectAllSimpleInfo();
            });

            if (completedQuizzes.isEmpty()) {
                for (Quiz quiz : quizzesSimpleInfo) {
                    completedQuizzes.put(quiz.getId(), "");
                }
            }
            logger.debug("completedQuizzes = " + completedQuizzes);

            if (answersMap != null && completedQuizId != null) {
                completedQuizzes.put(completedQuizId, "Completed! " + result);
            }

            session.setAttribute(COMPLETED_QUIZZES, completedQuizzes);
            logger.debug("set attribute '" + COMPLETED_QUIZZES + "' to " + completedQuizzes);
            req.setAttribute(ATTRIBUTE_QUIZZES_SIMPLE_INFO, quizzesSimpleInfo);
            logger.debug("set attribute '" + ATTRIBUTE_QUIZZES_SIMPLE_INFO + "'");
            // OK
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            logger.debug("PAGE_OK: RequestDispatcher.forward(...) to " + PAGE_OK);
        } catch (Exception e) {
            // FAIL
            e.printStackTrace();
            resp.sendRedirect(PAGE_ERROR);
            logger.debug("PAGE_OK: RequestDispatcher.forward(...) to " + PAGE_OK);
        }
    }
}

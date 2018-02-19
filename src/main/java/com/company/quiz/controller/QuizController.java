package com.company.quiz.controller;

import com.company.inject.DependencyInjectionServlet;
import com.company.inject.Inject;
import com.company.quiz.dao.QuestionDao;
import com.company.quiz.dao.QuizDao;
import com.company.quiz.dao.tx.TransactionManager;
import com.company.quiz.entity.Question;
import com.company.quiz.entity.Quiz;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.company.util.ClassName.getCurrentClassName;

public class QuizController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_QUIZ_TO_VIEW = "quiz";
    public static final String ATTRIBUTE_REDIRECT_TO_QUIZ_ID = "redirectToQuizId";
    public static final String FIRST_ID = "firstId";
    public static final String PAGE_OK = "quiz.jsp";
    public static final String PAGE_ERROR = "show-error.jsp";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Inject("txManager")
    private TransactionManager txManager;
    @Inject("quizDao")
    private QuizDao quizDao;
    @Inject("questionDao")
    private QuestionDao questionDao;

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null && !idStr.equals("")) {
            try {
                final Integer id = Integer.valueOf(idStr);
                Quiz quiz = txManager.call(() -> {
                    if (quizDao.selectInfoById(id) == null) {
                        logger.warn("No quiz for such id");
                    }
                    return quizDao.selectInfoById(id);
                });
                List<Question> questions = txManager.call(() -> questionDao.selectInfoByQuizId(quiz.getId()));
                int firstId = questions.get(0).getId();
                req.setAttribute(ATTRIBUTE_QUIZ_TO_VIEW, quiz);
                req.setAttribute(ATTRIBUTE_REDIRECT_TO_QUIZ_ID, idStr);
                req.setAttribute(FIRST_ID, firstId);
                logger.debug("set attribute '" + ATTRIBUTE_QUIZ_TO_VIEW + "' to " + quiz);
                logger.debug("set attribute '" + ATTRIBUTE_REDIRECT_TO_QUIZ_ID + "' to " + idStr);
                logger.debug("set attribute '" + FIRST_ID + "' to " + firstId);
                // OK
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                logger.debug("PAGE_OK: RequestDispatcher.forward(...) to " + PAGE_OK);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug("Some error", e);
            }
        }
        // ERROR
        resp.sendRedirect(PAGE_ERROR);
        logger.debug("PAGE_ERROR: RequestDispatcher.forward(...) to " + PAGE_ERROR);
    }
}

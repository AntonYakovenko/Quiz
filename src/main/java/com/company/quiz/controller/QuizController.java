package com.company.quiz.controller;

import com.company.inject.DependencyInjectionServlet;
import com.company.inject.Inject;
import com.company.quiz.dao.QuestionDao;
import com.company.quiz.dao.QuizDao;
import com.company.quiz.dao.exception.DaoException;
import com.company.quiz.dao.tx.TransactionManager;
import com.company.quiz.entity.Question;
import com.company.quiz.entity.Quiz;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.company.quiz.controller.SessionAttributes.COMPLETED_QUIZZES;
import static com.company.quiz.controller.SessionAttributes.CURRENT_QUIZ_ID;
import static com.company.quiz.controller.SessionAttributes.QUESTIONS_IDS_OF_CURRENT_QUIZ;
import static com.company.util.ClassName.getCurrentClassName;

@WebServlet(name = "quizController", urlPatterns = "/quiz.do")
public class QuizController extends DependencyInjectionServlet {
    static final String PARAM_ID = "id";
    private static final String ATTRIBUTE_QUIZ_TO_VIEW = "quiz";
    private static final String ATTRIBUTE_REDIRECT_TO_QUIZ_ID = "redirectToQuizId";
    private static final String ATTRIBUTE_QUIZ_COMPLETED = "completed";
    private static final String FIRST_ID = "firstId";
    private static final String PAGE_OK = "quiz.jsp";
    static final String PAGE_ERROR = "show-error.jsp";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Inject("txManager")
    TransactionManager txManager;
    @Inject("quizDao")
    QuizDao quizDao;
    @Inject("questionDao")
    private QuestionDao questionDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @SuppressWarnings("unchecked")
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
                // Detect completing quiz
                HttpSession session = req.getSession();
                Map<Integer, String> completedQuizzes = (Map<Integer, String>) session.getAttribute(COMPLETED_QUIZZES);
                if (completedQuizzes != null && completedQuizzes.get(id).contains("Completed!")) {
                    req.setAttribute(ATTRIBUTE_QUIZ_COMPLETED, true);
                    logger.debug("set attribute '" + ATTRIBUTE_QUIZ_COMPLETED + "' to " + ATTRIBUTE_QUIZ_COMPLETED);
                } else {
                    req.setAttribute(ATTRIBUTE_QUIZ_COMPLETED, false);
                    logger.debug("set attribute '" + ATTRIBUTE_QUIZ_COMPLETED + "' to " + ATTRIBUTE_QUIZ_COMPLETED);
                }

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
            } catch (SQLException | DaoException | NumberFormatException e) {
                e.printStackTrace();
                logger.debug("Some error", e);
            }
        }
        // ERROR
        resp.sendRedirect(PAGE_ERROR);
        logger.debug("PAGE_ERROR: RequestDispatcher.forward(...) to " + PAGE_ERROR);
    }
}

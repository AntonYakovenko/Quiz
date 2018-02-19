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
import java.io.IOException;
import java.util.List;

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
            List<Quiz> quizzesSimpleInfo;
            quizzesSimpleInfo = txManager.call(() -> {
                if (quizDao.selectAllSimpleInfo() == null) {
                    logger.warn("No quizzes!");
                }
                return quizDao.selectAllSimpleInfo();
            });
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

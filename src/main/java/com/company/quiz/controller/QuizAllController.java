package com.company.quiz.controller;

import com.company.quiz.dao.QuizDao;
import com.company.quiz.entity.Quiz;
import com.company.quiz.inject.DependencyInjectionServlet;
import com.company.quiz.inject.Inject;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.company.util.ClassName.getCurrentClassName;

public class QuizAllController extends DependencyInjectionServlet {
    public static final String ATTRIBUTE_QUIZZES_LIST = "quizzesList";
    public static final String PAGE_OK = "quizAll.jsp";
    public static final String PAGE_ERROR = "index.jsp";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

//    private QuizDao quizDao = new QuizDaoMock();
    @Inject("quizDao")
    private QuizDao quizDao;

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
            List<Quiz> quizList = quizDao.selectAll();
            req.setAttribute(ATTRIBUTE_QUIZZES_LIST, quizList);
            logger.debug("set attribute '" + ATTRIBUTE_QUIZZES_LIST + "'");
            // OK
            req.getRequestDispatcher(PAGE_OK).forward(req, resp);
            logger.debug("PAGE_OK: RequestDispatcher.forward(...) to " + PAGE_OK);
        } catch (Exception e) {
            // FAIL
            PrintWriter out = resp.getWriter();
            out.write("Some error. See stackTrace in console");
            e.printStackTrace();
//            response.sendRedirect(PAGE_ERROR);
//            logger.debug("PAGE_OK: RequestDispatcher.forward(...) to " + PAGE_OK);
        }
    }
}

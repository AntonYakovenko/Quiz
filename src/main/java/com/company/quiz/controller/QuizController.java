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
import java.io.PrintWriter;
import java.util.Arrays;

import static com.company.util.ClassName.getCurrentClassName;

public class QuizController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_MODEL_YO_VIEW = "quiz";
    public static final String ATTRIBUTE_REDIRECT_TO_QUIZ_ID = "redirectToQuizId";
    public static final String PAGE_OK = "quiz.jsp";
    public static final String PAGE_ERROR = "show-error.jsp";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Inject("txManager")
    private TransactionManager txManager;
    @Inject("quizDao")
    private QuizDao quizDao;

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
//                Quiz quiz = quizDao.selectById(id);
                Quiz model = txManager.call(() -> quizDao.selectById(id));
                req.setAttribute(ATTRIBUTE_MODEL_YO_VIEW, model);
                req.setAttribute(ATTRIBUTE_REDIRECT_TO_QUIZ_ID, idStr);
                logger.debug("set attribute '" + ATTRIBUTE_MODEL_YO_VIEW + "' to " + model);
                logger.debug("set attribute '" + ATTRIBUTE_REDIRECT_TO_QUIZ_ID + "' to " + idStr);
                // OK
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                logger.debug("PAGE_OK: RequestDispatcher.forward(...) to " + PAGE_OK);
                return;
            } catch (Exception e) {
                PrintWriter out = resp.getWriter();
//                out.write("Some error. See stackTrace in console" + "\n");
                out.write(Arrays.toString(e.getStackTrace()));
                e.printStackTrace();
                logger.debug("Some error", e);
            }
        }
        // ERROR
        resp.sendRedirect(PAGE_ERROR);
        logger.debug("PAGE_ERROR: RequestDispatcher.forward(...) to " + PAGE_ERROR);
    }
}

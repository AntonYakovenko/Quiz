package com.company.quiz.controller;

import com.company.inject.DependencyInjectionServlet;
import com.company.quiz.entity.Quiz;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.company.quiz.controller.SessionAttributes.QUIZZES_IN_BUCKET;
import static com.company.util.ClassName.getCurrentClassName;
import static java.util.Collections.unmodifiableList;

public class QuizRemoveFromBucketController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String PARAM_REDIRECT_TO_ID = "redirectToId";
    public static final String PAGE_ERROR = "quizAll.do";

    public static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(PARAM_ID);
        String idRedirectToStr = request.getParameter(PARAM_REDIRECT_TO_ID);
        if (idStr != null && idRedirectToStr != null) {
            Integer removeQuizId = Integer.valueOf(idStr);
            Integer redirectToQuizId = Integer.valueOf(idRedirectToStr);

            HttpSession session = request.getSession(false);
            if (session != null) {
                List<Quiz> oldBucket = (List<Quiz>) session.getAttribute(QUIZZES_IN_BUCKET);
                if (oldBucket != null) {
                    //todo: not-mutable bucket
                    List<Quiz> newBucket = new ArrayList<>(oldBucket);
                    for (Iterator<Quiz> iter = newBucket.iterator(); ; ) {
                        if (iter.next().getId() == removeQuizId) {
                            iter.remove();
                            session.setAttribute(QUIZZES_IN_BUCKET, unmodifiableList(newBucket));
                            break;
                        }
                    }
                    logger.debug("add NEXT quiz to session. Attribute = " + QUIZZES_IN_BUCKET);
                }
            }
            // OK
            String newLocation = "quiz.do?id=" + redirectToQuizId;
            response.sendRedirect(newLocation);
            logger.debug("PAGE_OK: response.sendRedirect(...) to " + newLocation);
            return;
        }
        // FAIL
        response.sendRedirect(PAGE_ERROR);
        logger.debug("PAGE_ERROR: response.sendRedirect(...) to " + PAGE_ERROR);
    }
}

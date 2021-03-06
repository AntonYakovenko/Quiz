package com.company.quiz.controller;

import com.company.inject.DependencyInjectionServlet;
import com.company.inject.Inject;
import com.company.quiz.dao.QuizDao;
import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.dao.exception.NoSuchEntityException;
import com.company.quiz.dao.tx.TransactionManager;
import com.company.quiz.entity.Quiz;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.company.quiz.controller.SessionAttributes.QUIZZES_IN_BUCKET;
import static com.company.util.ClassName.getCurrentClassName;
import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;

@WebServlet(name = "quizAddToBucketController", urlPatterns = "/quizAddToBucket.do")
public class QuizAddToBucketController extends DependencyInjectionServlet {
    private static final String PARAM_ID = "id";
    private static final String PAGE_ERROR = "quizAll.do";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Inject("quizDao")
    private QuizDao quizDao;
    @Inject("txManager")
    private TransactionManager txManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                Quiz quiz = null;
                try {
                    quiz = txManager.call(() -> {
                        if (quizDao.selectInfoById(id) == null) {
                            logger.debug("No quiz for such id");
                        }
                        return quizDao.selectInfoById(id);
                    });
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                HttpSession session = request.getSession(true);
                List<Quiz> oldBucket = (List<Quiz>) session.getAttribute(QUIZZES_IN_BUCKET);
                if (oldBucket == null) {
                    logger.debug("add FIRST quiz to session. Attribute = " + QUIZZES_IN_BUCKET);
                    session.setAttribute(QUIZZES_IN_BUCKET, singletonList(quiz));
                } else {
                    if (!oldBucket.contains(quiz)) {
                        List<Quiz> newBucket = new ArrayList<>(oldBucket);
                        newBucket.add(quiz);
//                        AtomicReference<List<Quiz>> ref = new AtomicReference<>();
//                        List<Quiz> oldVersion;
//                        ArrayList<Quiz> newVersion;
//                        do {
//                            oldVersion = ref.get();
//                            newVersion = new ArrayList<>(oldVersion);
//                            newVersion.add(quiz);
//                        } while (!ref.compareAndSet(oldVersion, newVersion));
                        logger.debug("add NEXT quiz to session. Attribute = " + QUIZZES_IN_BUCKET);
                        session.setAttribute(QUIZZES_IN_BUCKET, unmodifiableList(newBucket));
                    } else {
                        logger.debug("TRY to add quiz to session but has one (so don't add duplicates)");
                    }
                }
                // OK
                String newLocation = "quiz.do?id=" + id;
                response.sendRedirect(newLocation);
                logger.debug("PAGE_OK: response.sendRedirect(...) to " + newLocation);
                return;
            } catch (DaoSystemException e) {
                logger.warn(e);
                e.printStackTrace();
            }
        }
        // FAIL
        response.sendRedirect(PAGE_ERROR);
        logger.debug("PAGE_ERROR: response.sendRedirect(...) to " + PAGE_ERROR);
    }
}

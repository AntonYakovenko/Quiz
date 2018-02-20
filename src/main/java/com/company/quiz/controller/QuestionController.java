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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static com.company.quiz.controller.SessionAttributes.*;
import static com.company.util.ClassName.getCurrentClassName;

public class QuestionController extends DependencyInjectionServlet {
    public static final String PARAM_QUESTION_ID = "id";
    public static final String PARAM_ANSWER = "answer";
    public static final String ATTRIBUTE_QUESTION = "question";
    public static final String ATTRIBUTE_IS_LAST = "isLast";
    public static final String ATTRIBUTE_ANSWERS = "answers";
    //    public static final String ATTRIBUTE_CURRENT_QUESTION_ID = "currId";
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
        String answer = req.getParameter(PARAM_ANSWER);
        HttpSession session = req.getSession();
        List<Integer> questionsIdsList = (List<Integer>) session.getAttribute(QUESTIONS_IDS_OF_CURRENT_QUIZ);
        Map<Integer, Boolean> answersMap = new HashMap<>();

        try {
            final Integer id = Integer.valueOf(idQuestion);

            // Handle last answer
            if (id == -1) {
                int questionId = questionsIdsList.get(questionsIdsList.size() - 1);
                boolean userAnswer = answer.equals("true");
                answersMap.put(questionId, userAnswer);
                logger.debug("answerMap = " + answersMap);
                int quizId = (int) session.getAttribute(CURRENT_QUIZ_ID);
                Question question = new Question(-1, quizId);
                req.setAttribute(ATTRIBUTE_QUESTION, question);
                logger.debug("set attribute '" + ATTRIBUTE_QUESTION + "' to " + question);
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                logger.debug("PAGE_OK: RequestDispatcher.forward(...) to " + PAGE_OK);
                return;
            }

            Question question;
            List<Answer> answers;
            question = txManager.call(() -> {
                if (questionDao.selectInfoById(id) == null) {
                    logger.warn("No question for such id");
                }
                return questionDao.selectInfoById(id);
            });
            answers = txManager.call(() ->
            {
                if (answerDao.selectByQuestionId(question.getId()) == null) {
                    logger.warn("No answers for such question.id");
                }
                return answerDao.selectByQuestionId(question.getId());
            });

            // Defining next and last questions in quiz
            int currentQuizId = question.getQuizId();
            List<Integer> questionsIds = txManager.call(() -> questionDao.selectQuestionsIdsByQuizId(currentQuizId));
            session.setAttribute(QUESTIONS_IDS_OF_CURRENT_QUIZ, questionsIds);
            logger.debug("set attribute '" + QUESTIONS_IDS_OF_CURRENT_QUIZ + "' to " + questionsIds);
            boolean isLast = question.getName() == questionsIds.size();
            int nextQuestionId;
            if (isLast == false) {
                nextQuestionId = questionsIds.get(question.getId());
            } else {
                nextQuestionId = -1;
                session.setAttribute(CURRENT_QUIZ_ID, currentQuizId);
                logger.debug("set attribute '" + CURRENT_QUIZ_ID + "' to " + currentQuizId);
            }
            logger.debug("nextQuestionId = " + nextQuestionId);

            // Handle answer
            int previousQuestionId;
            if (questionsIdsList == null) {
                previousQuestionId = -1;
            } else {
                previousQuestionId = questionsIdsList.get(question.getName() - 2);
            }
            if (answer != null) {
                boolean userAnswer = answer.equals("true");
                if (previousQuestionId != -1) {
                    answersMap.put(previousQuestionId, userAnswer);
                }
                session.setAttribute(ANSWERS_MAP, answersMap);
                logger.debug("answersMap = " + answersMap);
            }

            req.setAttribute(ATTRIBUTE_QUESTION, question);
            req.setAttribute(ATTRIBUTE_IS_LAST, isLast);
            req.setAttribute(ATTRIBUTE_ANSWERS, answers);
//            req.setAttribute(ATTRIBUTE_CURRENT_QUESTION_ID, currentQuestionId);
            req.setAttribute(ATTRIBUTE_NEXT_QUESTION_ID, nextQuestionId);
            logger.debug("set attribute '" + ATTRIBUTE_QUESTION + "' to " + question);
            logger.debug("set attribute '" + ATTRIBUTE_IS_LAST + "' to " + isLast);
            logger.debug("set attribute '" + ATTRIBUTE_ANSWERS + "' to " + answers);
//            logger.debug("set attribute '" + ATTRIBUTE_CURRENT_QUESTION_ID + "' to " + ATTRIBUTE_CURRENT_QUESTION_ID);
            logger.debug("set attribute nextQuestionId '" + ATTRIBUTE_NEXT_QUESTION_ID + "' to " + nextQuestionId);
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

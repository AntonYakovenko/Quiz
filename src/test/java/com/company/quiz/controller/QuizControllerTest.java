package com.company.quiz.controller;

import com.company.quiz.dao.QuizDao;
import com.company.quiz.dao.tx.TransactionManager;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

import static com.company.quiz.controller.SessionAttributes.COMPLETED_QUIZZES;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class QuizControllerTest {
    private QuizController controller;
    private TransactionManager txManager;
    private QuizDao quizDao;

    private HttpServletRequest req;
    private HttpServletResponse resp;
    private HttpSession session;
    private RequestDispatcher dispatcher;

    @Before // todo make sth with session
    public void setUp() throws Throwable{
        controller = new QuizController();
        // txManager
        txManager = mock(TransactionManager.class);
        controller.txManager = txManager;
        // quizDao
        quizDao = mock(QuizDao.class);
        controller.quizDao = quizDao;
        // Servlet API
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        dispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void test_NoParam_QuizId() throws IOException, ServletException {
        // arrange
        when(req.getParameter(anyString())).thenReturn(null);
        // act
        controller.doGet(req, resp);
        // assert
        verify(req).getParameter(QuizController.PARAM_ID);
        verify(resp).sendRedirect(QuizController.PAGE_ERROR);
        verifyNoMoreInteractions(txManager, quizDao, req, resp, dispatcher);
    }

    @Test
    public void test_IncorrectParam_QuizId() throws IOException, ServletException {
        when(req.getParameter(anyString())).thenReturn("ABC");
        controller.doGet(req, resp);
        verify(req).getParameter(QuizController.PARAM_ID);
        verify(resp).sendRedirect(QuizController.PAGE_ERROR);
        verifyNoMoreInteractions(txManager, quizDao, req, resp, dispatcher);
    }

    @Test
    public void test_Dao_throw_DaoSystemException() throws IOException, ServletException {
        when(req.getParameter(anyString())).thenReturn("1");
        when(req.getSession().getAttribute(COMPLETED_QUIZZES)).thenReturn(new HashMap<Integer, String>());
        controller.doGet(req, resp);

        verify(req).getParameter(QuizController.PARAM_ID);
        verify(resp).sendRedirect(QuizController.PAGE_ERROR);
        verifyNoMoreInteractions(txManager, quizDao, req, resp, dispatcher);
    }

    @Test
    public void test_Quiz_NotExists() throws IOException, ServletException {
        when(req.getParameter(anyString())).thenReturn("1");
        controller.doGet(req, resp);
        verify(req).getParameter(QuizController.PARAM_ID);
        verify(resp).sendRedirect(QuizController.PAGE_ERROR);
        verifyNoMoreInteractions(txManager, quizDao, req, resp, dispatcher);
    }

    @Test
    public void test_Quiz_Exists() throws IOException, ServletException {
        when(req.getParameter(anyString())).thenReturn("1");
        controller.doGet(req, resp);
        verify(req).getParameter(QuizController.PARAM_ID);
        verify(dispatcher).forward(req, resp);
        verifyNoMoreInteractions(txManager, quizDao, req, resp, dispatcher);
    }
}
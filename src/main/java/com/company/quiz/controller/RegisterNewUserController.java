package com.company.quiz.controller;

import com.company.inject.DependencyInjectionServlet;
import com.company.inject.Inject;
import com.company.quiz.dao.UserDao;
import com.company.quiz.dao.exception.DaoSystemException;
import com.company.quiz.dao.exception.NoSuchEntityException;
import com.company.quiz.dao.tx.TransactionManager;
import com.company.quiz.entity.User;
import com.company.quiz.validator.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import static com.company.util.ClassName.getCurrentClassName;

public class RegisterNewUserController extends DependencyInjectionServlet {
    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_EMAIL = "email";
    public static final String USER = "user";
    public static final String PAGE_REGISTERED = "index.jsp";
    public static final String PAGE_MORE_INFO = "register.jsp";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Inject("txManager")
    private TransactionManager txManager;
    @Inject("userDao")
    private UserDao userDao;
    @Inject("userValidator")
    private UserValidator validator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter(PARAM_LOGIN);
        String name = req.getParameter(PARAM_NAME);
        String password = req.getParameter(PARAM_PASSWORD);
        final String email = req.getParameter(PARAM_EMAIL);

        if (login == null && name == null && password == null && email == null) {
            req.getRequestDispatcher(PAGE_MORE_INFO).forward(req, resp);
            logger.debug("requestDispatcher.forward(...) to " + PAGE_MORE_INFO);
            return;
        }

        final User tempUser = new User(-1, login, name, password, email);
        final Map<String, String> errorMap = validator.validate(tempUser);
        if (errorMap.isEmpty()) {
            User user;
            try {
                user = txManager.call(() -> {
                    try {
                        if (userDao.selectByLogin(login) != null) {
                            errorMap.put("login", "Such login exists!");
                            logger.debug("Found user with same login");
                        }
                    } catch (NoSuchEntityException | DaoSystemException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (userDao.selectByEmail(email) != null) {
                            errorMap.put("email", "Such email exists!");
                            logger.debug("Found user with same email");
                        }
                    } catch (DaoSystemException | NoSuchEntityException e) {
                        e.printStackTrace();
                    }
                    logger.debug("errorMap is: " + errorMap);
                    logger.debug("USER FOR REGISTRATION: user = '" + tempUser + "'");
                    return errorMap.isEmpty() ? userDao.insertNew(tempUser) : tempUser;
                });

                if (errorMap.isEmpty()) {
                    HttpSession session = req.getSession(true);
                    session.setAttribute(USER, user);
                    resp.sendRedirect(PAGE_REGISTERED);
                    logger.info("REGISTRATION - OK: sendRedirect to " + PAGE_REGISTERED);
                    return;
                }
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
        req.setAttribute("errorMap", errorMap);

        req.getRequestDispatcher(PAGE_MORE_INFO).forward(req, resp);
        logger.debug("REGISTRATION - FAIL: errorMap: " + errorMap);
    }
}
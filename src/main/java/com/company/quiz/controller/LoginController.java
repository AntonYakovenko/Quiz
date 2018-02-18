package com.company.quiz.controller;

import com.company.inject.DependencyInjectionServlet;
import com.company.inject.Inject;
import com.company.quiz.dao.UserDao;
import com.company.quiz.dao.exception.DaoException;
import com.company.quiz.dao.tx.TransactionManager;
import com.company.quiz.entity.User;
import com.company.quiz.validator.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static com.company.quiz.filter.security.UrlCodec.decode;
import static com.company.util.ClassName.getCurrentClassName;

public class LoginController extends DependencyInjectionServlet {
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String WRONG_AUTHORIZATION = "wrongAuthorization";
    public static final String USER = "user";
    public static final String PARAMETER_REDIRECT_TO = "redirectTo";
    public static final String PAGE_WRONG_AUTHORIZATION = "login.jsp";
    public static final String PAGE_DEFAULT = "index.jsp";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Inject("txManager")
    private TransactionManager txManager;
    @Inject("userDao")
    private UserDao userDao;
    @Inject("userValidator")
    private UserValidator validator;

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(PARAMETER_LOGIN);
        String password = req.getParameter(PARAMETER_PASSWORD);

        if (login == null && password == null) {
            req.getRequestDispatcher(PAGE_WRONG_AUTHORIZATION).forward(req, resp);
            logger.debug("requestDispatcher.forward(...) to " + PAGE_WRONG_AUTHORIZATION);
            return;
        }

        final User tempUser = new User(-1, login, "Name", password, "email@exaple.com");
        final Map<String, String> errorMap = validator.validate(tempUser);
        User user;
        try {
            user = txManager.call(() -> {
                if (userDao.selectByLogin(login) == null) {
                    errorMap.put("login", "No user for such login");
                    logger.debug("No user for such login");
                    return tempUser;
                }
                return userDao.selectByLogin(login);
            });
        } catch (SQLException | DaoException e) {
            throw new RuntimeException(e);
        }
        if (!user.getPassword().equals(password)) {
            errorMap.put("password", "Incorrect password");
            logger.debug("Incorrect password");
        }
        if (errorMap.isEmpty()) {
            logger.info("LOGIN - OK: user = '" + user + "'");
            HttpSession session = req.getSession(true);
            session.setAttribute(USER, user);
            String redirectToEncoded = req.getParameter(PARAMETER_REDIRECT_TO);
            logger.debug("locationRedirectTo = '" + redirectToEncoded + "'");
            if (redirectToEncoded != null && !redirectToEncoded.equals("")) {
                String redirectToDecoded = decode(redirectToEncoded);
                resp.sendRedirect(redirectToDecoded);
                logger.debug("PAGE_OK: sendRedirect to " + redirectToDecoded);
                return;
            } else {
                resp.sendRedirect(PAGE_DEFAULT);
                logger.debug("PAGE_DEFAULT: sendRedirect to " + PAGE_DEFAULT);
                return;
            }
        }
        req.setAttribute("errorMap", errorMap);
        req.getRequestDispatcher(PAGE_WRONG_AUTHORIZATION).forward(req, resp);
        logger.debug("PAGE_WRONG_AUTHORIZATION: requestDispatcher.forward(...) to " + PAGE_WRONG_AUTHORIZATION);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        processRequest(req, resp);
    }
}

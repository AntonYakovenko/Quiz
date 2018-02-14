package com.company.quiz.filter;

import com.company.quiz.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.company.quiz.filter.security.UrlCodec.decode;
import static com.company.util.ClassName.getCurrentClassName;

public class LoginFilter extends BaseFilter implements Filter {
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String WRONG_AUTHORIZATION = "wrongAuthorization";
    public static final String USER = "user";
    public static final String PARAMETER_REDIRECT_TO = "redirectTo";
    public static final String PAGE_WRONG_AUTHORIZATION = "login.jsp";
    public static final String PAGE_DEFAULT = "index.jsp";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    private Map<String, User> mem = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        mem.put("Mike", new User("Mike", "123"));
        mem.put("Sara", new User("Sara", "123"));
    }

    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String login = req.getParameter(PARAMETER_LOGIN);
        String password = req.getParameter(PARAMETER_PASSWORD);
        User user = new User(login, password);
        if (login != null && password != null && user.equals(mem.get(login))) {
            // LOGIN - OK
            logger.info("LOGIN - OK, user = '" + user + "'");
            HttpSession session = req.getSession(true);
            session.setAttribute(USER, user);
            String redirectToEncoded = req.getParameter(PARAMETER_REDIRECT_TO);
            logger.trace("locationRedirectTo = '" + redirectToEncoded + "'");
            if (redirectToEncoded != null) {
                String redirectToDecoded = decode(redirectToEncoded);
                resp.sendRedirect(redirectToDecoded);
                logger.debug("PAGE_OK: sendRedirect to " + redirectToDecoded);
            } else {
                resp.sendRedirect(PAGE_DEFAULT);
                logger.debug("PAGE_DEFAULT: sendRedirect to " + PAGE_DEFAULT);
            }
        } else {
            req.setAttribute(WRONG_AUTHORIZATION, "Wrong login or password");
            req.getRequestDispatcher(PAGE_WRONG_AUTHORIZATION).forward(req, resp);
            logger.debug("PAGE_WRONG_AUTHORIZATION: requestDispatcher.forward(...) to " + PAGE_WRONG_AUTHORIZATION);
        }
    }
}

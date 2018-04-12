package com.company.quiz.controller;

import com.company.inject.DependencyInjectionServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.company.quiz.filter.UrlCodec.decode;
import static com.company.util.ClassName.getCurrentClassName;

@WebServlet(name = "logoutController", urlPatterns = "/logout.do")
public class LogoutController extends DependencyInjectionServlet {
    private static final String PARAMETER_NAME_REDIRECT_TO = "redirectTo";
    private static final String BASE_PAGE = "index.jsp";

    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectToEncoded = req.getParameter(PARAMETER_NAME_REDIRECT_TO);
        logger.debug("encodedURL = " + redirectToEncoded);
        req.getSession().invalidate();
//        resp.sendRedirect("login.jsp");
        if (redirectToEncoded != null) {
            String redirectToDecoded = decode(redirectToEncoded);
            logger.debug("sendRedirect to " + redirectToDecoded);
            resp.sendRedirect(redirectToDecoded);
        } else {
            logger.warn("sendRedirect to base page = '" + BASE_PAGE + "'. Not correct");
            resp.sendRedirect(BASE_PAGE);
        }
    }
}

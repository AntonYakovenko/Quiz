package com.company.quiz.filter;

import org.apache.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.company.quiz.controller.SessionAttributes.LOCALE;

@WebFilter(filterName = "languageFilter", urlPatterns = "/language")
public class LanguageFilter extends BaseFilter {
    public static final String BASE_PAGE = "index.jsp";
    public static final String PARAM_LANGUAGE = "language";

    public static final Logger logger = Logger.getLogger(LanguageFilter.class);

    @Override
    public void doHttpFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String language = req.getParameter(PARAM_LANGUAGE);
        session.setAttribute(LOCALE, language);
        logger.debug("set attribute '" + LOCALE + "' to " + language);
        resp.sendRedirect(BASE_PAGE);
    }
}

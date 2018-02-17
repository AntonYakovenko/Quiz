package com.company.quiz.filter;

import com.company.inject.DependencyInjectionFilter;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.company.quiz.controller.SessionAttributes.USER;
import static com.company.quiz.filter.security.UrlCodec.encode;
import static com.company.util.ClassName.getCurrentClassName;

public class SecurityFilter extends DependencyInjectionFilter implements Filter {
    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getRequestURI().contains("login.do")) {
            chain.doFilter(request, response);
            return;
        }

        logger.trace("doHttpFilter call");
        if (request.getSession() != null && request.getSession().getAttribute(USER) != null) {
            // авторизирован: сессия существует и содержит пользователя
            // просто пропускаем запрос дальше
            chain.doFilter(request, response);
            logger.trace("chain.doFilter(request, response);");
        } else {
            // неавторизирован:
            // 1. делаем "внешний redirect" на login.jsp
            // 2. в URL передаем путь куда шли
            // 3. путь закодирован по схеме {'?' -> '!', '=' -> '*', '&' -> '@'}
            // response. sendRedirect("login.jsp?redirectTo=DESTINATION!parameterA#valueA
            String codedOriginPage = request.getRequestURI()
                    + encode((request.getQueryString() == null) ? "" : "?" + request.getQueryString());

            logger.trace("sec>> codedOriginPage = " + codedOriginPage);
            String redirectTo = "login.jsp?redirectTo=" + codedOriginPage;
            response.sendRedirect(redirectTo);
            logger.trace("response.sendRedirect(" + redirectTo + ");");
        }
    }
}

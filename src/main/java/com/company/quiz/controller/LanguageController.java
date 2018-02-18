package com.company.quiz.controller;

import com.company.inject.DependencyInjectionServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.company.quiz.controller.SessionAttributes.LOCALE;

public class LanguageController extends DependencyInjectionServlet {
    public static final String PAGE_TEST = "test-test.jsp";
    public static final String PARAM_LANGUAGE = "language";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String language = request.getParameter(PARAM_LANGUAGE);
        session.setAttribute(LOCALE, language);
        response.sendRedirect(request.getParameter("address"));
    }
}

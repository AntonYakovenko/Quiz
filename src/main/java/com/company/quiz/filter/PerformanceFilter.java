package com.company.quiz.filter;

import com.company.inject.DependencyInjectionFilter;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.company.util.ClassName.getCurrentClassName;

public class PerformanceFilter extends DependencyInjectionFilter implements Filter {
    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        long inTime = System.nanoTime();
        chain.doFilter(request, response);
        long outTime = System.nanoTime();
        logger.debug("dT = "  + (outTime - inTime));
    }
}

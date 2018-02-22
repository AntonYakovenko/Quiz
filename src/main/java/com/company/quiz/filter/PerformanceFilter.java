package com.company.quiz.filter;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.company.util.ClassName.getCurrentClassName;

@WebFilter(filterName = "performanceFilter", urlPatterns = "/*")
public class PerformanceFilter extends BaseFilter implements Filter {
    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    public void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        long inTime = System.nanoTime();
        chain.doFilter(request, response);
        long outTime = System.nanoTime();
        logger.trace("dT = "  + (outTime - inTime));
    }
}

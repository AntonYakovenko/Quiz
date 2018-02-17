package com.company.inject;

import com.company.quiz.filter.BaseFilter;
import org.apache.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static com.company.util.ClassName.getCurrentClassName;

public abstract class DependencyInjectionFilter extends BaseFilter {
    private static final String APP_CTX_PATH = "contextConfigLocation";
    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            // then inject from AppContext to all marked by @Inject fields
            List<Field> allFields = FieldReflector.collectUpTo(this.getClass(), DependencyInjectionFilter.class);
            List<Field> injectFields = FieldReflector.filterInject(allFields);

            for (Field field : injectFields) {
                field.setAccessible(true);
                Inject annotation = field.getAnnotation(Inject.class);
                logger.debug("I find method marked by @Inject: " + field);
                String beanName = annotation.value();
                logger.debug("I must instantiate and inject '" + beanName + "'");
                Object bean = AppContext.getInstance().getBean(beanName);
                logger.debug("Instantiation - OK: '" + beanName + "'");
                if (bean == null) {
                    throw new ServletException("There isn't bean with name '" + beanName + "'");
                }
                field.set(this, bean);
            }
        } catch (Exception e) {
            throw new ServletException("Can't inject by from " + APP_CTX_PATH, e);
        }
    }

    @Override
    public abstract void doHttpFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;
}

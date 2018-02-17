package com.company.inject;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.reflect.Field;
import java.util.List;

import static com.company.util.ClassName.getCurrentClassName;

public class DependencyInjectionServlet extends HttpServlet {
    private static final String APP_CTX_PATH = "contextConfigLocation";

//    private static final Logger logger = Logger.getLogger(ClassName.getCurrentClassName);
    private static final Logger logger = Logger.getLogger(getCurrentClassName());

    @Override
    public void init() throws ServletException {
        String appCtxPath = this.getServletContext().getInitParameter(APP_CTX_PATH);
        logger.trace("load " + APP_CTX_PATH + " -> " + appCtxPath);

        if (appCtxPath == null) {
            logger.error("I need init param " + APP_CTX_PATH);
            throw new ServletException(APP_CTX_PATH + " init param == null");
        }

        try {
            //inject from AppContext to all marked by @Inject fields
            List<Field> allFields = FieldReflector.collectUpTo(this.getClass(), DependencyInjectionServlet.class);
            List<Field> injectFields = FieldReflector.filterInject(allFields);

            for (Field field : injectFields) {
                field.setAccessible(true);
                Inject annotation = field.getAnnotation(Inject.class);
                logger.trace("I find method marked by @Inject: " + field);
                String beanName = annotation.value();
                logger.trace("I must instantiate and inject '" + beanName + "'");
                Object bean = AppContext.getInstance().getBean(beanName);
                logger.trace("Instantiation - OK: '" + beanName + "'");
                if (bean == null) {
                    throw new ServletException("There isn't bean with name '" + beanName + "'");
                }
                field.set(this, bean);
            }
        } catch (Exception e) {
            throw new ServletException("Can't inject by from " + APP_CTX_PATH, e);
        }
    }
}

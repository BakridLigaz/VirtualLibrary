package com.ligaz.server;

import com.ligaz.server.config.WebConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ApplicationInit implements WebApplicationInitializer {

    private static final String NAME_DISPATCHER = "dispatcher";

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebConfig.class);
        servletContext.addListener(new ContextLoaderListener(applicationContext));
        ServletRegistration.Dynamic servlet = servletContext.addServlet(NAME_DISPATCHER,new DispatcherServlet(applicationContext));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}

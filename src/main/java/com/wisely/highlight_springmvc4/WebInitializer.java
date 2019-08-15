package com.wisely.highlight_springmvc4;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//WebApplicationInitializer是Spring提供用来配置Servlet
//3.0+配置的接口，从而实现了替代web.xml的位置。实现此接
//口将会自动被SpringServletContainerInitializer（用来启动
//Servlet 3.0容器）获取到。
public class WebInitializer implements WebApplicationInitializer {//1

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyMvcConfig.class);
        //新建WebApplicationContext，注册配置类，并将其和当
        //前servletContext关联。
        ctx.setServletContext(servletContext); //2
        //注册Spring MVC的DispatcherServlet。
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx)); //3
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        servlet.setAsyncSupported(true);//3 此句开启异步方法支持。
    }
}
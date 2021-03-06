package com.wisely.highlight_springmvc4;

import com.wisely.highlight_springmvc4.interceptor.DemoInterceptor;
import com.wisely.highlight_springmvc4.messageconverter.MyMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

//此处无任何特别，只是一个普通的Spring配置类。这里我
//们配置了一个JSP的ViewResolver，用来映射路径和实际页面
//的位置，其中，@EnableWebMvc注解会开启一些默认配置，
//如一些ViewResolver或者MessageConverter等。
@Configuration
@EnableWebMvc //①@EnableWebMvc开启SpringMVC支持，若无此句，重写 WebMvcConfigurerAdapter方法无效。
@EnableScheduling //在MyMvcConfig上开始计划任务的支持，使用@EnableScheduling
@ComponentScan("com.wisely.highlight_springmvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter {//②继承WebMvcConfigurerAdapter类，重写其方法可对 Spring MVC进行配置。

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //可能读者对路径前缀配置为/WEB-INF/classes/views/有些奇
        //怪，怎么和我开发的目录不一致？因为看到的页面效果是运行
        //时而不是开发时的代码，运行时代码会将我们的页面自动编译
        //到/WEB-INF/classes/views/下，图4-2是运行时的目录结构，这
        //样我们就能理解前缀为什么写成这样，在Spring Boot中，我们
        //将使用Thymeleaf作为模板，因而不需要这样的配置。
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
    //在此处要特别解释一下Spring MVC的ViewResolver，这是
    //Spring MVC视图（JSP下就是html）渲染的核心机制；Spring
    //MVC里有一个接口叫做ViewResolver（我们的ViewResolver都
    //实现该接口），实现这个接口要重写方法
    //resolveViewName（），这个方法的返回值是接口View，而
    //View的职责就是使用model、request、response对象，并将渲染
    //的视图（不一定是html，可能是json、xml、pdf）返回给浏览
    //器。


    /**
     * 程序的静态文件（js、css、图片）等需要直接访问，这时
     * 我们可以在配置里重写addResourceHandlers方法来实现
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //③addResourceLocations指的是文件放置的目录， addResourceHandler指的是对外暴露的访问路径。
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");//3
    }

    @Bean
    //配置拦截器的Bean。
    public DemoInterceptor demoInterceptor() {
        return new DemoInterceptor();
    }

    @Override
    //重写addInterceptors方法，注册拦截器。
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }

    //此处无任何业务处理，只是简单的页面转向

    /**
     * 可以写成这样
     *
     * @RequestMapping("/index") public String hello(){ return "index";}
     * 也可以在配置文件里直接配置，这样实现的代码更简洁，管理更集中
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/converter").setViewName("/converter");
        registry.addViewController("/sse").setViewName("/sse");
        registry.addViewController("/async").setViewName("/async");
    }

    //在Spring MVC中，路径参数如果带“.”的话，“.”后面的值将被忽略
    //通过重写configurePathMatch方法可不忽略“.”后面的参数
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    /**
     * 更多配置请查看WebMvcConfigurerAdapter类的API。因其
     * 是WebMvcConfigurer接口的实现，所以WebMvcConfigurer的
     * API内的方法也可以用来配置MVC。
     */

    //Spring MVC通过 配置一个MultipartResolver来上传文件
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        return multipartResolver;
    }

    /**
     * 配置自定义的HttpMessageConverter的Bean，在Spring MVC
     * 里注册HttpMessageConverter有两个方法：
     * <p>
     * 1.configureMessageConverters：重载会覆盖掉Spring MVC默
     * 认注册的多个HttpMessageConverter。
     * <p>
     * 2.extendMessageConverters：仅添加一个自定义的
     * HttpMessageConverter，不覆盖默认注册的 HttpMessageConverter。
     * <p>
     * 所以在此例中我们重写extendMessageConverters：
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    @Bean
    public MyMessageConverter converter() {
        return new MyMessageConverter();
    }
}
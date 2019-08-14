package com.wisely.highlight_springmvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//利用@Controller注解声明是一个控制器。
@Controller//1
public class HelloController {
    //利用@RequestMapping配置URL和方法之间的映射。
    @RequestMapping("/index")//2
    public String hello() {
        //通过上面ViewResolver的Bean配置，返回值为index，说
        //明我们的页面放置的路径为/WEB-INF/classes/views/index.jsp。
        return "index"; //3
    }
}
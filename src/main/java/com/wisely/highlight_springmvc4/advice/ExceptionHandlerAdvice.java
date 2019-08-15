package com.wisely.highlight_springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

//1定制ControllerAdvice
//@ControllerAdvice声明一个控制器建言，
// @ControllerAdvice组合了@Component注解，所以自动注册为 Spring的Bean。
@ControllerAdvice //1
public class ExceptionHandlerAdvice {

    //②@ExceptionHandler在此处定义全局处理，通过
    // @ExceptionHandler的value属性可过滤拦截的条件，在此处我
    // 们可以看出我们拦截所有的Exception。
    @ExceptionHandler(value = Exception.class) //2
    public ModelAndView exception(Exception exception, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");// error 页面
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }

    /**@ModelAttribute：@ModelAttribute本来的作用是绑定键值
     * 对到Model里，此处是让全局的@RequestMapping都能获得在 此处设置的键值对。
     */
    //此处使用@ModelAttribute注解将键值对添加到全局，所
    // 有注解的@RequestMapping的方法可获得此键值对
    @ModelAttribute //3
    public void addAttributes(Model model) {
        model.addAttribute("msg", "额外信息"); //3
    }

    /**
     * @InitBinder：用来设置WebDataBinder，WebDataBinder用
     * 来自动绑定前台请求参数到Model中。
     */
    //通过@InitBinder注解定制WebDataBinder
    @InitBinder //4
    public void initBinder(WebDataBinder webDataBinder) {
        //此处演示忽略request参数的id，更多关于WebDataBinder
        // 的配置，请参考WebDataBinder的API文档
        webDataBinder.setDisallowedFields("id"); //5
    }
}
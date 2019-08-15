package com.wisely.highlight_springmvc4.web.ch4_3;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wisely.highlight_springmvc4.domain.DemoObj;

@Controller // 1 @Controller注解声明此类是一个控制器。
@RequestMapping("/anno") //2 @RequestMapping（“/anno”）映射此类的访问路径 是/anno
public class DemoAnnoController {
    //produces可定制返回的response的媒体类型和字符集，或需返回
    //值是json对象，则设置produces=“application/json； charset=UTF-8”
    @RequestMapping(produces = "text/plain;charset=UTF-8") // 3
    public @ResponseBody
    String index(HttpServletRequest request) { // 4
        return "url:" + request.getRequestURL() + " can access";
    }

    //演示接受路径参数，并在方法参数前结合@PathVariable 使用，访问路径为/anno/pathvar/xx。
    @RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8")// 5
    public @ResponseBody
    String demoPathVar(@PathVariable String str, HttpServletRequest request) {
        return "url:" + request.getRequestURL() + " can access,str: " + str;
    }

    //演示常规的request参数获取，访问路径 为/anno/requestParam?id=1。
    @RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8") //6
    public @ResponseBody
    String passRequestParam(Long id, HttpServletRequest request) {
        return "url:" + request.getRequestURL() + " can access,id: " + id;
    }

    //演示解释参数到对象，访问路径为/anno/obj?id=1&name=xx。
    @RequestMapping(value = "/obj", produces = "application/json;charset=UTF-8")//7
    @ResponseBody //8
    public String passObj(DemoObj obj, HttpServletRequest request) {
        return "url:" + request.getRequestURL() + " can access, obj id: " + obj.getId() + " obj name:" + obj.getName();
    }

    //演示映射不同的路径到相同的方法，访问路径 为/anno/name1或/anno/name2。
    @RequestMapping(value = {"/name1", "/name2"}, produces = "text/plain;charset=UTF-8")//9
    public @ResponseBody
    String remove(HttpServletRequest request) {
        return "url:" + request.getRequestURL() + " can access";
    }
}
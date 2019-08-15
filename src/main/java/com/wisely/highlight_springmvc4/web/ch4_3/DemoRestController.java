package com.wisely.highlight_springmvc4.web.ch4_3;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wisely.highlight_springmvc4.domain.DemoObj;

@RestController //1
@RequestMapping("/rest")
public class DemoRestController {
    //返回数据的媒体类型为json。
    //http://localhost:8080/rest/getjson?id=1&name=xx
    @RequestMapping(value = "/getjson", produces = {"application/json;charset=UTF-8"}) //2
    public DemoObj getjson(DemoObj obj) {
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy");//3 直接返回对象，对象会自动转换成json。
    }

    //返回数据的媒体类型为xml。
    //http://localhost:8080/rest/getxml?id=1&name=xx
    @RequestMapping(value = "/getxml", produces = {"application/xml;charset=UTF-8"})//4
    public DemoObj getxml(DemoObj obj) {
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy");//5 直接返回对象，对象会自动转换为xml。
    }
}
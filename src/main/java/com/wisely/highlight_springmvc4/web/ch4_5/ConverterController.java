package com.wisely.highlight_springmvc4.web.ch4_5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wisely.highlight_springmvc4.domain.DemoObj;

@Controller
public class ConverterController {

    //①指定返回的媒体类型为我们自定义的媒体类型 application/x-wisely
    @RequestMapping(value = "/convert", produces = {"application/x-wisely"}) //1

    public @ResponseBody
    DemoObj convert(@RequestBody DemoObj demoObj) {
        return demoObj;
    }
}
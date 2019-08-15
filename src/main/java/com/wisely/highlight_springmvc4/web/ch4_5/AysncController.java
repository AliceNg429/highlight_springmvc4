package com.wisely.highlight_springmvc4.web.ch4_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import com.wisely.highlight_springmvc4.service.PushService;

//控制器
//异步任务的实现是通过控制器从另外一个线程返回一个
//DeferredResult，这里的DeferredResult是从pushService中获得
//的。
@Controller
public class AysncController {
    @Autowired
    PushService pushService; //1 定时任务，定时更新DeferredResult

    @RequestMapping("/defer")
    @ResponseBody
    //返回给客户端DeferredResult
    public DeferredResult<String> deferredCall() { //2
        return pushService.getAsyncUpdate();
    }
}
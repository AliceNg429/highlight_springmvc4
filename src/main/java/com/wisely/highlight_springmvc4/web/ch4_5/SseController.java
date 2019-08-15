package com.wisely.highlight_springmvc4.web.ch4_5;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//SSE的服务器端推送（推送方案是当客户端向服务器端发送请求，服务端会抓住这个请求不放，等
// 有数据更新的时候才返回给客户端，当客户端接送到消息后，再向服务器端发送请求，周而复始
// 这种方法的好处是减少了服务器的请求数量，大大减少了服务器的压力）
@Controller
public class SseController {
    //注意，这里使用输出的媒体类型为text/event-stream，这
    //是服务器端SSE的支持，本例演示每5秒钟向浏览器推送随机
    //消息。
    @RequestMapping(value = "/push", produces = "text/event-stream") //1
    public @ResponseBody
    String push() {
        Random r = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:Testing 1,2,3" + r.nextInt() + "\n\n";
    }
}
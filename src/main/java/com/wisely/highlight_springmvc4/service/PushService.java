package com.wisely.highlight_springmvc4.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class PushService {
    //①在PushService里产生DeferredResult给控制器使用，通过
    //@Scheduled注解的方法定时更新DeferredResult
    private DeferredResult<String> deferredResult; //1

    public DeferredResult<String> getAsyncUpdate() {//1
        deferredResult = new DeferredResult<String>();
        return deferredResult;
    }

    @Scheduled(fixedDelay = 5000)
    public void refresh() {//1
        if (deferredResult != null) {
            deferredResult.setResult(new Long(System.currentTimeMillis()).toString());
        }
    }
}
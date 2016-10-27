package com.nb.daipengfei.run;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/*********************************
 *                               *
 Created by daipengfei on 16/9/28.
 *                               *
 ********************************/
@Component
@EnableRetry
public class RetryComponent {
    @Resource
    private InitBean initBean;

    @Retryable(value = RuntimeException.class,backoff = @Backoff(1000))
    public void testRetry(String name){
        System.out.println("hi" + name + ":" + new Date());
    }

    @Recover
    public void recover(RuntimeException e){
        System.out.println("recover" + e.getMessage());
    }
}

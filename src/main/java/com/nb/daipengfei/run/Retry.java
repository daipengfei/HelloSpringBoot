package com.nb.daipengfei.run;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

/*********************************
 *                               *
 Created by daipengfei on 16/9/28.
 *                               *
 ********************************/
@Component
@EnableRetry
public class Retry {

    @Retryable(RuntimeException.class)
    public void testRetry(String name) {
        System.out.println("--------------------------retry11111111!!!!------------" + new Date());
        if (1 == 1) {
            throw new RuntimeException("haha11111111!!!!");
        }
        System.out.println("hi1" + name + ":" + new Date());
    }

    @Retryable(RuntimeException.class)
    public void testRetry2(String name) {
        System.out.println("--------------------------retry22222222!!!!------------" + new Date());
        if (1 == 1) {
            throw new RuntimeException("haha22222222!!!!");
        }
        System.out.println("hi2" + name + ":" + new Date());
    }

    @Recover
    public void recover(RuntimeException e) {
        System.out.println("recover" + e.getMessage());
    }
}

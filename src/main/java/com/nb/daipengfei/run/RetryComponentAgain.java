package com.nb.daipengfei.run;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Component;

/*********************************
 *                               *
 Created by daipengfei on 16/9/28.
 *                               *
 ********************************/
@Component
@EnableRetry
public class RetryComponentAgain implements RetryInterface{

    public void testRetry(String name){
        System.out.println("--------------------------retryAgain------------" + new Date());
        if(1==1){
            throw new RuntimeException("hahaAgain");
        }
        System.out.println("hi" + name + ":" + new Date());
    }


    public void recover(RuntimeException e){
        System.out.println("recoverAgain" + e.getMessage());
    }
}

package com.nb.daipengfei.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*********************************
 *                               *
 Created by daipengfei on 16/9/28.
 *                               *
 ********************************/

@Aspect
@Component
public class RetryAspect {

    @Around("@annotation(com.nb.daipengfei.annotation.Retry)")
    public Object retry(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("hello");
        return joinPoint.proceed();
    }

    @Around("@annotation(com.nb.daipengfei.annotation.MockClient)")
    public Object test(ProceedingJoinPoint joinPoint) throws Throwable {
        String s = "java.lang.String";
        return Class.forName(s).newInstance();
    }

}

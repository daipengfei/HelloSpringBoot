package com.nb.daipengfei.annotation;

import java.lang.annotation.*;

/*********************************
 *                               *
 Created by daipengfei on 16/9/28.
 *                               *
 ********************************/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface Retry {

    int retryTimes() default 3;

}

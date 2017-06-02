package com.nb.daipengfei.bean;

import org.springframework.stereotype.Component;

/**
 * Created by daipengfei
 * on 2017/5/24.
 */

@Component
public class InnerBean {

    public InnerBean() throws InterruptedException {
        Thread.sleep(10000);
    }

    public void sayHi(){
        System.out.println("hi");
    }
}

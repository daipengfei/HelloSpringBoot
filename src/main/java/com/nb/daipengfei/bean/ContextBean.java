package com.nb.daipengfei.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/*********************************
 *                               *
 Created by daipengfei on 16/10/10.
 *                               *
 ********************************/
@Component
public class ContextBean {
    @Resource
    private AppCtxUtil appCtxUtil;

    @PostConstruct
    public void init() throws InterruptedException {
//        Thread.sleep(Integer.MAX_VALUE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                execute();
            }
        }).start();
    }

    private void execute() {
        for(int i=0;i<2;i++){
            ServiceBean bean = appCtxUtil.getBean(ServiceBean.class);
            bean.sayHello("world!!!!");
        }
    }
}

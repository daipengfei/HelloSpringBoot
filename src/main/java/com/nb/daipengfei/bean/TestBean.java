package com.nb.daipengfei.bean;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*********************************
 *                               *
 Created by daipengfei on 16/12/9.
 *                               *
 ********************************/
@Component
public class TestBean {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @Resource
    private InnerBean innerBean;

    public TestBean() {

    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

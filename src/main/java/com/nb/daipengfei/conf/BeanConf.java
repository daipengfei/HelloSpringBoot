package com.nb.daipengfei.conf;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.google.common.eventbus.AsyncEventBus;
import com.nb.daipengfei.bean.ManagerBean;

/*********************************
 *                               *
 Created by daipengfei on 16/8/30.
 *                               *
 ********************************/
@Configuration
public class BeanConf {

    @Bean
    @Scope("prototype")
    public ManagerBean managerBean(){
        return new ManagerBean();
    }


    @Bean
    public AsyncEventBus asyncEventBus(){
        AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(5));
        asyncEventBus.register(managerBean());
        return asyncEventBus;
    }
}

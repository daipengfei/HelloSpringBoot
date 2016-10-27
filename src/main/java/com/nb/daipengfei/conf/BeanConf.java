package com.nb.daipengfei.conf;

import java.util.concurrent.Executors;

import com.enniu.cloud.bean.BeanEntity.LaterBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.google.common.eventbus.AsyncEventBus;
import com.nb.daipengfei.bean.ManagerBean;
import com.nb.daipengfei.bean.ServiceBean;

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
        System.out.println("init manager bean");
        return new ManagerBean();
    }


    @Bean
    public AsyncEventBus asyncEventBus(){
        System.out.println("init asyncEventBus");
        AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(5));
        asyncEventBus.register(managerBean());
        return asyncEventBus;
    }
}

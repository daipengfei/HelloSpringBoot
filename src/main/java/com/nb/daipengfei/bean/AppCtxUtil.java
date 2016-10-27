package com.nb.daipengfei.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/*********************************
 *                               *
 Created by daipengfei on 16/10/10.
 *                               *
 ********************************/
@Component
public class AppCtxUtil implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> requireType) {
        if (null != requireType) {
            return this.applicationContext.getBean(requireType);
        }
        return null;
    }
}

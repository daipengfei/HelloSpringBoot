package com.nb.daipengfei.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*********************************
 *                               *
 Created by daipengfei on 16/10/8.
 *                               *
 ********************************/
@Component
@ConfigurationProperties(prefix = "com.enniu")
public class PropertiesBean {

    private String attr;

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }
}

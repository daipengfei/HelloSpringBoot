package com.nb.daipengfei.bean;

import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/*********************************
 *                               *
 Created by daipengfei on 16/8/30.
 *                               *
 ********************************/
@Component
public  class ServiceBean {

    private String name;

    @Lookup
    public  ManagerBean managerBean(){
        return null;
    }


    @Subscribe
    public void sayHello(String name){
        System.out.println("hello: "+name);
    }

    @Subscribe
    public void sayHi(String name){
        System.out.println("hi: "+name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("hash:"+managerBean().hashCode());
        this.name = name;
    }

}

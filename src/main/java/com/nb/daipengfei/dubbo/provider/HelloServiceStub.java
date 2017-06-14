package com.nb.daipengfei.dubbo.provider;

import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * Created by daipengfei
 * on 2017/6/6.
 */

public class HelloServiceStub implements HelloService {

    private Environment environment;

    private HelloService helloService;

    public HelloServiceStub(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        System.out.println("read from cache");
        System.out.println("env : " + environment);
        if(true){
            return "cache";
        }
        return helloService.sayHello(name);
    }
}

package com.nb.daipengfei.hystrix;

import java.util.concurrent.ExecutionException;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/*********************************
 *                               *
 Created by daipengfei on 16/10/8.
 *                               *
 ********************************/

public class CommandHelloWorld extends HystrixCommand<String>{

    private final String name;

    public CommandHelloWorld(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
        .andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
        this.name = name;
    }

    @Override
    protected String run() throws InterruptedException {
        return "Hello " + name + "!";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final CommandHelloWorld bob = new CommandHelloWorld("Bob");
        for(int i =0 ;i < 1; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String s = bob.execute();
                    System.out.println(s);
                }
            }).start();
        }
    }

    @Override
    protected String getFallback(){
        return "hi";
    }

}

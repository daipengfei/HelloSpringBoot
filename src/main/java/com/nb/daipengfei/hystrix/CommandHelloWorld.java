package com.nb.daipengfei.hystrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/*********************************
 *                               *
 Created by daipengfei on 16/10/8.
 *                               *
 ********************************/

public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                .withExecutionIsolationSemaphoreMaxConcurrentRequests(50))
            .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(50)));
        this.name = name;
    }

    @Override
    protected String run() throws InterruptedException {
        //        if(1 ==1){
        //            throw new RuntimeException();
        //        }
        System.out.println("running in:" + Thread.currentThread().getName());
        return "Hello " + name + "!";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 12; i++) {
            //            new Thread(new Runnable() {
            //                @Override
            //                public void run() {
            new CommandHelloWorld("Bob").execute();
            //                }
            //            }).start();
        }
    }

    @Override
    protected String getFallback() {
        System.out.println("hi");
        return "hi";
    }

}

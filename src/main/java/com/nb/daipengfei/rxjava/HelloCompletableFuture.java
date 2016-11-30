package com.nb.daipengfei.rxjava;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

/*********************************
 *                               *
 Created by daipengfei on 16/11/30.
 *                               *
 ********************************/

public class HelloCompletableFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                new Supplier<String>() {
            @Override
            public String get() {
                System.out.println(Thread.currentThread());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    //ignore
                }
                return "hello world!";
            }
        },executorService);

        completableFuture.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s + " : " + Thread.currentThread());
            }
        });

        System.out.println("hi");
    }
}

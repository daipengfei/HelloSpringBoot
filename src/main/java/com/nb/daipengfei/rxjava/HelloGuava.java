package com.nb.daipengfei.rxjava;

import com.google.common.util.concurrent.*;

import javax.annotation.Nullable;
import java.util.concurrent.*;

/*********************************
 *                               *
 Created by daipengfei on 16/11/8.
 *                               *
 ********************************/

public class HelloGuava {
    public static void main(String[] args) {
        ListeningExecutorService executorService =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(5));
        final ListenableFuture<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getId());
                return "hi";
            }
        });

        future.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getId());
                    System.out.println(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        }, executorService);

        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println(Thread.currentThread().getId());
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {

            }

        },executorService);
        System.out.println("hi guava");
        executorService.shutdown();
    }
}

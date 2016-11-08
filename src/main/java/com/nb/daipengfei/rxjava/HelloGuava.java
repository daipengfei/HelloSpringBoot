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
                Thread.sleep(5000);
                return "hi";
            }
        });

//        future.addListener(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println(future.get());
//                } catch (InterruptedException | ExecutionException e) {
//                    //ignore
//                }
//            }
//        }, Executors.newFixedThreadPool(5));

        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {

            }

        });
        System.out.println("hi guava");
        executorService.shutdown();
    }
}

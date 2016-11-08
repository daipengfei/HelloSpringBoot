package com.nb.daipengfei.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/*********************************
 *                               *
 Created by daipengfei on 16/11/8.
 *                               *
 ********************************/

public class HelloRxJava {
    public static void main(String[] args) throws InterruptedException {
        final Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //ignore
                }
                subscriber.onNext("hi");
                subscriber.onCompleted();
            }
        });

        observable.subscribeOn(Schedulers.newThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String name) {
                System.out.println(name + ":" + Thread.currentThread().getName());
            }
        });

        System.out.println("Hi rxJava!");

        Thread.sleep(100000);
    }
}

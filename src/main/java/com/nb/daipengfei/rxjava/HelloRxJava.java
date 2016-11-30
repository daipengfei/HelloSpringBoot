package com.nb.daipengfei.rxjava;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/*********************************
 *                               *
 Created by daipengfei on 16/11/8.
 *                               *
 ********************************/

public class HelloRxJava {
    public static void main(String[] args) throws InterruptedException {
        final Observable<String> observable = Observable.create(
                new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println(Thread.currentThread().getName());
                subscriber.onNext("hi");
                subscriber.onCompleted();
            }
        });

        observable.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io()).subscribe(new Action1<String>() {
            @Override
            public void call(String name) {
                System.out.println(name + ":" + Thread.currentThread().getName());
            }
        });

        System.out.println("Hi rxJava!");
    }
}

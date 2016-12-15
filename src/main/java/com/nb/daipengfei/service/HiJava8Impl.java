package com.nb.daipengfei.service;

/*********************************
 *                               *
 Created by daipengfei on 16/12/15.
 *                               *
 ********************************/

public class HiJava8Impl implements HiJava8,NiceJava8 {
    public static void main(String[] args) {
        HiJava8 hiJava8 = new HiJava8Impl();
        hiJava8.init();
        hiJava8.initAgain();
    }

    @Override
    public void init() {
        HiJava8.super.init();
    }
}

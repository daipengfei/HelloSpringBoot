package com.nb.daipengfei.service;

/*********************************
 *                               *
 Created by daipengfei on 16/12/15.
 *                               *
 ********************************/

public interface HiJava8 {
    default void init(){
        System.out.println("init");
    }

    default void initAgain(){
        System.out.println("initAgain");
    }

    static void sayHi(){
        System.out.println("hi");
    }

}

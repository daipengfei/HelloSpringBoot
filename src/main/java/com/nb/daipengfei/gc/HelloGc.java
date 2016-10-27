package com.nb.daipengfei.gc;

/*********************************
 *                               *
 Created by daipengfei on 16/10/12.
 *                               *
 ********************************/

public class HelloGc {

    public static void alloc(){
        byte[] b = new byte[1024];
        b[0] = 1;
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 10*1024; i++){
            alloc();
        }
        System.out.println("init end!");
        Thread.sleep(Integer.MAX_VALUE);
    }
}

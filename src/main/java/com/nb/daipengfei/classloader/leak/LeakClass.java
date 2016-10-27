package com.nb.daipengfei.classloader.leak;

/*********************************
 *                               *
 Created by daipengfei on 16/9/12.
 *                               *
 ********************************/

public class LeakClass implements LeakClassInterface {
    private static final long[] cache = new long[1000000];

}

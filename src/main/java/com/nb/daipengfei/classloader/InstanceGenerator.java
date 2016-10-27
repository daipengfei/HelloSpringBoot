package com.nb.daipengfei.classloader;

import com.nb.daipengfei.classloader.leak.LeakClassInterface;

/*********************************
 *                               *
 Created by daipengfei on 16/9/12.
 *                               *
 ********************************/

public class InstanceGenerator {

    static LeakClassLoader classLoader = LeakClassLoader.instance();

    public static LeakClassInterface newInstance() throws IllegalAccessException,
            InstantiationException, ClassNotFoundException {
        
        return (LeakClassInterface)
                classLoader.loadClass("com.nb.daipengfei.classloader.leak.LeakClass")
                        .newInstance();
    }
}


package com.nb.daipengfei.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/*********************************
 *                               *
 Created by daipengfei on 16/9/12.
 *                               *
 ********************************/
public class LeakClassLoader extends URLClassLoader {
    public LeakClassLoader() {
        super(new URL[] { getContextClassLoaderClassPath() });
    }

//    @Override
//    public Class<?> loadClass(String className) throws ClassNotFoundException {
//        if (className != null && className.equals("com.nb.daipengfei.classloader.leak.LeakClass")) {
//            return findClass(className);
//        } else {
//            return super.loadClass(className);
//        }
//    }

    public Class<?> findClass(String className) throws ClassNotFoundException {
        if (className != null && className.equals("com.nb.daipengfei.classloader.leak.LeakClass")){
            return findClass(className);
        }else {
            return super.loadClass(className);
        }
    }

    static URL getContextClassLoaderClassPath() {
        return Thread.currentThread().getContextClassLoader().getResource(".");
    }

    public static LeakClassLoader instance(){
        return instance;
    }

    private static final LeakClassLoader instance = new LeakClassLoader();
}
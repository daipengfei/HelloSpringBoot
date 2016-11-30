package com.nb.daipengfei.reflect;

import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.Method;

/*********************************
 *                               *
 Created by daipengfei on 16/11/11.
 *                               *
 ********************************/

public class UnitTest {
    @Test
    public void testFixedValue() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloCglib.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "hello cglib!";
            }
        });
        HelloCglib proxy = (HelloCglib) enhancer.create();
        System.out.println(proxy.hello(null));
    }

    @Test
    public void testInvocationHandler() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloCglib.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "Hello cglib!";
                } else {
                    throw new RuntimeException("Do not know what to do.");
                }
            }
        });
        HelloCglib proxy = (HelloCglib) enhancer.create();
        System.out.println(proxy.hello(null));
        System.out.println(proxy.toString());
    }

    @Test
    public void testMethodInterceptor() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloCglib.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                    throws Throwable {
                if(method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "Hello cglib!";
                } else {
                    return proxy.invokeSuper(obj, args);
                }
            }
        });
        HelloCglib proxy = (HelloCglib) enhancer.create();
        System.out.println(proxy.hello(null));
        System.out.println(proxy);
        System.out.println(proxy.hashCode()); // Does not throw an exception or result in an endless loop.
    }
}

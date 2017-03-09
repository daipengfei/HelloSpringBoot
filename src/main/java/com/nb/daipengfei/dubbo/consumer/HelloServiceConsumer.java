package com.nb.daipengfei.dubbo.consumer;

import com.nb.daipengfei.dubbo.provider.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * Created by daipengfei
 * on 2017/3/3.
 */
public class HelloServiceConsumer {
	public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/dubbo-consumer.xml");
		Object helloService = context.getBean("helloService");
		Method sayHello = helloService.getClass().getDeclaredMethod("sayHello", String.class);

	}
}

package com.nb.daipengfei.dubbo.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by daipengfei
 * on 2017/3/3.
 */

public class HelloServiceConsumer {
	public static void main(String[] args)
			throws InvocationTargetException,
			IllegalAccessException, NoSuchMethodException {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("/dubbo-consumer.xml");

	}
}

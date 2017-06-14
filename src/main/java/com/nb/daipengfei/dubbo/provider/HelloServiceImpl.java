package com.nb.daipengfei.dubbo.provider;

import com.alibaba.dubbo.rpc.RpcException;
import com.netflix.config.DynamicPropertyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by daipengfei
 * on 2017/2/27.
 */
public class HelloServiceImpl implements HelloService {
	@Override
	public String sayHello(String name) {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			//ignore
		}
		System.out.println("received from client!");
		return "Hello " + name;
	}

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/dubbo-service.xml");
		Thread.sleep(Integer.MAX_VALUE);
	}
}

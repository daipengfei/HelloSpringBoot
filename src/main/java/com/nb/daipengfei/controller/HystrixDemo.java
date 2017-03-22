package com.nb.daipengfei.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Created by daipengfei
 * on 2017/2/26.
 */

public class HystrixDemo extends HystrixCommand<String> {
	private boolean isFallback;

	public HystrixDemo() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.
				Factory.asKey(HystrixDemo.class.getSimpleName()))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(50)));
	}

	@Override
	protected String run() throws InterruptedException {
		Thread.sleep(100);
		System.out.println(Thread.currentThread().getName());
		return "Hello World!";
	}

	@Override
	protected String getFallback() {
		isFallback = true;
		System.out.println("fallback: " + Thread.currentThread().getName());
		return "fall back!";
	}

	public boolean isFallback() {
		return isFallback;
	}


	@Override
	public String toString() {
		return "HystrixDemo{" +
				"isFallback=" + isFallback +
				'}';
	}
}

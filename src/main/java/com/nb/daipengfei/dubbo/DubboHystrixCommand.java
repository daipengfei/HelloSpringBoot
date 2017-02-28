package com.nb.daipengfei.dubbo;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcResult;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

/**
 * Created by daipengfei
 * on 2017/2/27.
 */

public class DubboHystrixCommand extends HystrixCommand<Result> {
	private final Invoker<?> invoker;

	private final Invocation invocation;

	public DubboHystrixCommand(Invoker<?> invoker, Invocation invocation) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(invoker.getInterface().getName() + "." + invocation.getMethodName()))
				.andCommandKey(HystrixCommandKey.Factory.asKey(invoker.getInterface().getName() + "." + invocation.getMethodName())));
		this.invoker = invoker;
		this.invocation = invocation;
	}

	@Override
	protected Result run() throws Exception {
		return invoker.invoke(invocation);
	}

	@Override
	protected Result getFallback() {
		return new RpcResult();
	}
}

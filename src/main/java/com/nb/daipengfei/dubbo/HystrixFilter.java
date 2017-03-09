package com.nb.daipengfei.dubbo;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * Created by daipengfei
 * on 2017/2/27.
 */

//@Activate(group = Constants.CONSUMER)
public class HystrixFilter implements Filter {
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		return new DubboHystrixCommand(invoker, invocation).execute();
	}
}

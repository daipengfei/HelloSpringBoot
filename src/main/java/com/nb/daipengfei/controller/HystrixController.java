package com.nb.daipengfei.controller;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by daipengfei
 * on 2017/2/26.
 */

@RestController
@RequestMapping("/hystrix")
public class HystrixController {

	@RequestMapping("/limit")
	public String threadLimit(@RequestParam int size) throws InterruptedException {
		if (size <= 0) {
			return "size must be greater than 0!";
		}
		Executor executor = Executors.newFixedThreadPool(size);
		CountDownLatch latch = new CountDownLatch(size);
		AtomicInteger successCount = new AtomicInteger(0);
		AtomicInteger fallbackCount = new AtomicInteger(0);
		LinkedList<String> errorMsg = new LinkedList<>();
		final HystrixRequestContext context = HystrixRequestContext.initializeContext();
		for (int i = 0; i < size; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						synchronized (context) {
							HystrixRequestContext.setContextOnCurrentThread(context);
							HystrixDemo command = new HystrixDemo();
							command.execute();
							System.out.println(Thread.currentThread().getName() + command.isResponseFromCache());
							if (command.isFallback()) {
								fallbackCount.addAndGet(1);
							} else {
								successCount.addAndGet(1);
							}
						}
					} catch (Exception e) {
						errorMsg.add(e.getMessage());
					} finally {
						latch.countDown();
					}
				}
			});
		}
		latch.await();
		context.shutdown();
		int success = successCount.get();
		int fallback = fallbackCount.get();
		if (errorMsg.isEmpty()) {
			return "success count = " + success + "<br> fallback count = " + fallback;
		}
		return "success count = " + success + "<br> fallback count = " + fallback +
				"<br> fail count = " + (size - success) +
				"<br> error message : " + errorMsg.getLast();
	}

}

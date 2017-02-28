package com.nb.daipengfei.gc;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/*********************************
 *                               *
 Created by daipengfei on 16/10/12.
 *                               *
 ********************************/

public class HelloGc {

	private volatile boolean busy;

	private volatile boolean init;

	AtomicInteger counter = new AtomicInteger(0);

	CountDownLatch latch = new CountDownLatch(10);

	@Test
	public void test() throws InterruptedException {
		new HelloGc();
		if (init) {
			busy = true;
		}
		latch.await();
		System.out.println(counter.get());
	}


	public HelloGc() {
		for (int i = 0; i < 10; i++) {
			InternalThread internalThread = new InternalThread();
			internalThread.start();
		}
		init = true;
	}

	private class InternalThread extends Thread {

		@Override
		public void run() {
			try {
				if (busy) {
					counter.addAndGet(1);
				}
			} finally {
				latch.countDown();
			}
		}

	}
}

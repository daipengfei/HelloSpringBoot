package com.nb.daipengfei.controller;

import com.nb.daipengfei.bean.ServiceBean;
import com.nb.daipengfei.dubbo.provider.HelloService;
import com.netflix.config.DynamicPropertyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/*********************************
 *                               *
 Created by daipengfei on 16/8/25.
 *                               *
 ********************************/

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ServiceBean serviceBean;

//	@Resource
//	private HelloService helloService;

	@RequestMapping("/{id:.+}")
	public User view(@PathVariable("id") String id) {
		User user = new User();
		user.setId(id);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return user;
	}

	@RequestMapping(value = "/service/{name}", method = RequestMethod.GET)
	public ServiceBean service(@PathVariable("name") String name) {
		serviceBean.setName(name);
		return serviceBean;
	}

	@RequestMapping(value = "/services/test", method = RequestMethod.POST)
	public
	@ResponseBody
	int testPost(@Valid @RequestBody User user) {
		System.out.println(user);
		return 0;
	}

	@RequestMapping(value = "/dynamic")
	public String archaius() {
		DynamicPropertyFactory instance = DynamicPropertyFactory.getInstance();
		return instance.getStringProperty("hello", "world").get();
	}

//	@RequestMapping(value = "/dubbo")
//	public String dubbo(@RequestParam int concurrent) throws InterruptedException {
//		if (concurrent <= 0) {
//			return "size must be greater than 0!";
//		}
//		Executor executor = Executors.newFixedThreadPool(concurrent);
//		CountDownLatch latch = new CountDownLatch(concurrent);
//		AtomicInteger successCount = new AtomicInteger(0);
//		AtomicInteger fallbackCount = new AtomicInteger(0);
//		LinkedList<String> errorMsg = new LinkedList<>();
//		for (int i = 0; i < concurrent; i++) {
//			executor.execute(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						String s = helloService.sayHello("World!");
//						if (s == null) {
//							fallbackCount.addAndGet(1);
//						} else {
//							successCount.addAndGet(1);
//						}
//					} catch (Exception e) {
//						errorMsg.add(e.getMessage());
//					} finally {
//						latch.countDown();
//					}
//				}
//			});
//		}
//		latch.await();
//		int success = successCount.get();
//		int fallback = fallbackCount.get();
//		if (errorMsg.isEmpty()) {
//			return "success count = " + success + "<br> fallback count = " + fallback;
//		}
//		return "success count = " + success + "<br> fallback count = " + fallback +
//				"<br> fail count = " + (concurrent - success) +
//				"<br> error message : " + errorMsg.getLast();
//	}


	public static final class User {
		private String id;

		@NotNull
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "User{" +
					"id=" + id +
					", name='" + name + '\'' +
					'}';
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Executor executor = new ThreadPoolExecutor(
				0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		for (int i = 0; i < 900000000; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        //ignore
//                    }
					System.out.println(Thread.currentThread());
				}
			});
//            Thread.sleep(1000);
		}
	}

}

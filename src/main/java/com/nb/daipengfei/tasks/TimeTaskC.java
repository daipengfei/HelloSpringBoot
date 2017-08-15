package com.nb.daipengfei.tasks;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.Date;

/*********************************
 *                               *
 Created by daipengfei on 16/10/8.
 *                               *
 ********************************/
@Component
@EnableScheduling
public class TimeTaskC {

	@Scheduled(cron = "3/20 * * * * ?")
	public void schedule() throws InterruptedException {
		Thread.sleep(100);
		System.out.println(Thread.currentThread().getName() + " : C " + new Date());
	}

	public static String getFromBase64(String s) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		return new String(decoder.decodeBuffer(s), "utf-8");
	}
}

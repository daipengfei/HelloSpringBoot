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
public class TimeTaskB {

	@Scheduled(cron = "1/30 * * * * ?")
	public void schedule() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " : B " + new Date());
	}

	public static String getFromBase64(String s) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		return new String(decoder.decodeBuffer(s), "utf-8");
	}
}

package com.nb.daipengfei.tasks;

import java.io.IOException;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;

/*********************************
 *                               *
 Created by daipengfei on 16/10/8.
 *                               *
 ********************************/
//@Component
public class TimeTask {

    @Scheduled(cron = "0/10 * * * * ? ")
    public void schedule() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getId() + " : a "+ new Date());
    }

    public static String getFromBase64(String s) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return new String(decoder.decodeBuffer(s), "utf-8");
    }

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }
}

package com.nb.daipengfei.tasks;

import java.io.IOException;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import sun.misc.BASE64Decoder;

/*********************************
 *                               *
 Created by daipengfei on 16/10/8.
 *                               *
 ********************************/
//@Component
public class TimeTask {

    @Scheduled(fixedRate = 5000)
    public void schedule() {
        System.out.println(new Date());
    }

    public static String getFromBase64(String s) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return new String(decoder.decodeBuffer(s), "utf-8");
    }
}

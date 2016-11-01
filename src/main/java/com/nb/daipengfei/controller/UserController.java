package com.nb.daipengfei.controller;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nb.daipengfei.bean.ServiceBean;
import retrofit.http.POST;

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

    @RequestMapping(value = "/service/{name}",method = RequestMethod.GET)
    public ServiceBean service(@PathVariable("name") String name) {
        serviceBean.setName(name);
        return serviceBean;
    }

    @RequestMapping(value = "/services/test",method = RequestMethod.POST)
    public @ResponseBody int testPost(@RequestBody User user){
        System.out.println(user);
        return 0;
    }



    public static final class User {
        private String         id;

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
                0,Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
        for(int i = 0; i < 900000000;i++) {
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

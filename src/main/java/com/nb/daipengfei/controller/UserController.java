package com.nb.daipengfei.controller;

import java.util.Arrays;
import java.util.List;

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

}

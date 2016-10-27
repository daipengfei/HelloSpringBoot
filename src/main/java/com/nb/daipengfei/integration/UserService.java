package com.nb.daipengfei.integration;

import com.nb.daipengfei.controller.UserController;
import jdk.nashorn.internal.objects.annotations.Getter;
import retrofit.http.GET;
import retrofit.http.Path;

/*********************************
 *                               *
 Created by daipengfei on 16/9/11.
 *                               *
 ********************************/

public interface UserService {

    @GET("/user/{id}")
    UserController.User getUser(@Path("id") Long id);

}

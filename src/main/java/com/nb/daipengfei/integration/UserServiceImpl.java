package com.nb.daipengfei.integration;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nb.daipengfei.controller.UserController;

import retrofit.RestAdapter;

/*********************************
 *                               *
 Created by daipengfei on 16/9/11.
 *                               *
 ********************************/
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private RestAdapter resAdapter;

    @Override
    public UserController.User getUser(Long id) {
        return resAdapter.create(UserService.class).getUser(id);
    }
}

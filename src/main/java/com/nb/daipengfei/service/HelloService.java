package com.nb.daipengfei.service;

import retrofit.http.GET;

import java.util.List;
import java.util.Map;

/*********************************
 *                               *
 Created by daipengfei on 16/10/8.
 *                               *
 ********************************/

public interface HelloService {
    @GET("/v1/kv/services/hello-service")
    List<Map<String, String>> getConfig();
}

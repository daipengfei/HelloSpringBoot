package com.nb.daipengfei.conf;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.JacksonConverter;

/*********************************
 *                               *
 Created by daipengfei on 16/9/11.
 *                               *
 ********************************/
@Configuration
public class ResAdapterConf {

    @Bean
    public RestAdapter restAdapter() {
        return new RestAdapter.Builder().setEndpoint("http://localhost:8080").setClient(shortOkClient())
            .setConverter(jacksonConverter()).build();
    }

    @Bean
    public JacksonConverter jacksonConverter() {
        return new JacksonConverter(objectMapper());
    }

    @Bean
    public OkClient shortOkClient() {
        return new OkClient(shortHttpClient());
    }

    @Bean
    public OkHttpClient shortHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(2000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(2000, TimeUnit.MILLISECONDS);
        return okHttpClient;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

}

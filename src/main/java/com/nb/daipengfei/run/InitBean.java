package com.nb.daipengfei.run;

import javax.annotation.Resource;

import com.nb.daipengfei.annotation.MockClient;
import com.nb.daipengfei.bean.TestBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Component;

import com.nb.daipengfei.bean.ContextBean;
import com.nb.daipengfei.bean.PropertiesBean;

import redis.clients.jedis.JedisPool;

/*********************************
 *                               *
 Created by daipengfei on 16/8/30.
 *                               *
 ********************************/

@Component
public class InitBean implements ApplicationRunner {

    @Resource
    private JedisPool jedisPool;

    @Resource
    private RetryInterface retryComponent;

    @Resource
    private RetryInterface retryComponentAgain;

    @Resource
    private Retry retry;

    @Resource
    private PropertiesBean propertiesBean;

    @Resource
    ContextBean contextBean;

    @Resource
    private TestBean testBean;

    @Value("com.hello.key")
    private String helloKey;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println(helloKey);
        String s= propertiesBean.testCast();
        System.out.println("hahahah: " + s.equals(""));
//        System.out.println("hi: " + testBean.getId());
//        String test = contextBean.test();
//        System.out.println(test);
//        retry.testRetry("good");
//        retry.testRetry2("nice");
//        retryComponent.testRetry("world");
//        retryComponentAgain.testRetry("hi");
//        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://localhost:8500").build();
//        List<Map<String, String>> config = adapter.create(HelloService.class).getConfig();
//        Properties properties = new Properties();
//        for (Map<String, String> map : config) {
//            properties.load(new StringReader(getFromBase64(map.get("Value"))));
//        }
//        System.out.println(properties);
//        System.out.println(propertiesBean.getAttr());
    }

}

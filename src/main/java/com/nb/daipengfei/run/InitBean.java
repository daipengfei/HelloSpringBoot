package com.nb.daipengfei.run;

import static com.nb.daipengfei.tasks.TimeTask.getFromBase64;

import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import com.enniu.cloud.bean.BeanEntity.LaterBean;
import com.enniu.cloud.bean.TaskConf;
import com.nb.daipengfei.bean.ContextBean;
import com.nb.daipengfei.bean.PropertiesBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.nb.daipengfei.service.HelloService;

import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.JedisPool;
import retrofit.RestAdapter;

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
    private PropertiesBean propertiesBean;

    @Resource
    private LaterBean later;

    @Resource
    ContextBean contextBean;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        String test = contextBean.test();
        System.out.println(test);
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
        System.out.println(later);
    }

}

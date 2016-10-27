package com.nb.daipengfei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.enniu.cloud.bean.BeanConf;
import com.enniu.cloud.bean.TaskConf;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/*********************************
 *                               *
 Created by daipengfei on 16/8/25.
 *                               *
 ********************************/
@SpringBootApplication(exclude = RabbitAutoConfiguration.class)
@Import({ BeanConf.class, TaskConf.class })
//@EnableScheduling
public class App {

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class)
            .initializers(new ApplicationContextInitializer<GenericApplicationContext>() {
                @Override
                public void initialize(GenericApplicationContext applicationContext) {
                    applicationContext.setAllowBeanDefinitionOverriding(false);
                }
            }).run(args);
//        new SpringApplicationBuilder(App.class).run(args);
//        SpringApplication.run(App.class,args);
    }

}

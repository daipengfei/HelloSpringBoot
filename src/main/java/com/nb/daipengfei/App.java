package com.nb.daipengfei;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;

import com.enniu.cloud.bean.BeanConf;
import com.enniu.cloud.bean.TaskConf;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.SocketUtils;

/*********************************
 *                               *
 Created by daipengfei on 16/8/25.
 *                               *
 ********************************/
@SpringBootApplication(exclude = RabbitAutoConfiguration.class)
@Import({ BeanConf.class, TaskConf.class })
@EnableScheduling
public class App {

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class)
            .initializers(new ApplicationContextInitializer<GenericApplicationContext>() {
                @Override
                public void initialize(GenericApplicationContext applicationContext) {
                    applicationContext.setAllowBeanDefinitionOverriding(false);
                }
            }).run(args);
    }

    @Bean
    public Integer port() {
        return 8080;
//        return SocketUtils.findAvailableTcpPort();
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }

    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(port());
        return connector;
    }


}

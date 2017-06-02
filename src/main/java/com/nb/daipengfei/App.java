package com.nb.daipengfei;

import com.nb.daipengfei.conf.ConsulPropertySource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;

/*********************************
 *                               *
 Created by daipengfei on 16/8/25.
 *                               *
 ********************************/

@SpringBootApplication(exclude = {RabbitAutoConfiguration.class,
		MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class})
@EnableScheduling
@EnableAspectJAutoProxy
//@ImportResource("/dubbo-consumer.xml")
@ImportResource("/job.xml")
public class App {

//    @Resource
//    private TestBean testBean;

	public static void main(String[] args) {
		new SpringApplicationBuilder(App.class)
				.initializers(new ApplicationContextInitializer<GenericApplicationContext>() {
					@Override
					public void initialize(GenericApplicationContext applicationContext) {
//						applicationContext.setAllowBeanDefinitionOverriding(false);
//						ConfigurableEnvironment env = applicationContext.getEnvironment();
//						env.getPropertySources().addLast(new ConsulPropertySource(
//								"service/config.properties", new HashMap<>()));
					}
				}).run(args);
	}

//	@Bean
//	public Integer port() {
//		return 8082;
////        return SocketUtils.findAvailableTcpPort();
//	}

//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
//        return tomcat;
//    }
//
//    private Connector createStandardConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setPort(port());
//        return connector;
//    }


}

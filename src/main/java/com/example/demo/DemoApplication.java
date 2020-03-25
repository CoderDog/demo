package com.example.demo;

import com.example.service.base.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.Assert;

import java.util.concurrent.Executors;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class DemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	@Autowired
	private BaseService<String> baseService;

	public static void main(String[] args) {
//		ConfigurableApplicationContext configurableApplicationContext =
//				SpringApplication.run(DemoApplication.class, args);
		//disabled banner, don't want to see the spring logo
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		ConfigurableApplicationContext applicationContext = app.run(args);
		Executors.newSingleThreadExecutor();
		DemoApplication demoApplication = (DemoApplication) applicationContext.getBean("demoApplication");
		demoApplication.baseService.add();
//		logger.info(configurableApplicationContext.toString());
//		Assert.hasText("","hehe");
	}
}

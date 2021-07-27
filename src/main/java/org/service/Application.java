package org.service;


import org.service.aggregate.LogsAggService;
import org.service.util.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
		LogsAggService logsAggService= new LogsAggService();
		
		for (String arg : args) {
			log.info("<Application> Arguments : {}", arg);
			logsAggService.postLoginDataBoot(ApplicationUtil.getAppArguments(arg));
		}
	}

}

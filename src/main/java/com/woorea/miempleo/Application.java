package com.woorea.miempleo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.woorea.miempleo.domain.JobPosition;
import com.woorea.miempleo.service.JobPositionService;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
	
	@Bean
	public InitializingBean init(JobPositionService service) {
		System.out.println("init db");
		return () -> {
			service.save(new JobPosition("Java Developer"));
			service.save(new JobPosition("AngularJS Developer"));
		};
	}

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

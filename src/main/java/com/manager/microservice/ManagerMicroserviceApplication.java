package com.manager.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ManagerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerMicroserviceApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// TODO Auto-generated method stub
				WebMvcConfigurer.super.addCorsMappings(registry);
				registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("*").maxAge(3600L).allowedHeaders("*")
				.exposedHeaders("Authorization").allowCredentials(true);
			}
			
		};
		
	}

}

package com.example.demo;



import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;

import com.example.demo.service.AsyncServices;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
@EnableSwagger2
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
public class SpringSwaggerCarsApplication implements CommandLineRunner {
	@Resource 
	AsyncServices services;
	Logger log = LoggerFactory.getLogger(this.getClass().getName());

	public static void main(String[] args) {
		SpringApplication.run(SpringSwaggerCarsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Future<String> process1 = services.process();
		Future<String> process2 = services.process();
		Future<String> process3 = services.process();
		
		// Wait until They are all Done
		// If all are not Done. Pause 2s for next re-check
		while(!(process1.isDone() && process2.isDone() && process3.isDone())){
			Thread.sleep(2000);
		}
		log.info("All Processes are DONE!");
		// Log results
		log.info("Process 1: " + process1.get());
		log.info("Process 2: " + process2.get());
		log.info("Process 3: " + process3.get());
	}

	@Bean
	public Docket carsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("spring-swagger-api")
				.apiInfo(apiInfo())
				.select()	
//				.paths(regex ("/cars.*"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Spring REST Sample with Swagger")
				.description("Spring REST Sample with Swagger")
				.license("Apache License Version 2.0")
				.licenseUrl("#")
				.version("1.0")
				.build();
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}


}

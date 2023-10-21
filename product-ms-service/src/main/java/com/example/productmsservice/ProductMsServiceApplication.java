package com.example.productmsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.model")
@EnableJpaRepositories("com.dao")
@ComponentScan("com")
@EnableEurekaClient
@SpringBootApplication
public class ProductMsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMsServiceApplication.class, args);
	}

}

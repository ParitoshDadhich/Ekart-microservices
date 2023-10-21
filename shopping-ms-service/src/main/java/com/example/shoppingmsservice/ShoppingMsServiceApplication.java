package com.example.shoppingmsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EntityScan("com.model")
@EnableJpaRepositories("com.dao")
@ComponentScan("com")
@EnableEurekaClient
public class ShoppingMsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingMsServiceApplication.class, args);
	}
	
	// for initializing webclient throught reactie	
		@Bean("webclient")
		@LoadBalanced
		public WebClient.Builder getWebClient(){
			return WebClient.builder();
		}

}

package com.example.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServiceApplication {

	/*
	Netflix Eureka is a critical component in building and managing microservices-based applications,
	where service registration and discovery are essential for seamless communication between services.
	It works well with other components of the Netflix OSS stack and is often used in conjunction with
	tools like Ribbon for client-side load balancing and Hystrix for fault tolerance.
	Eureka has gained popularity in the microservices community and is widely used in
	cloud-native and containerized applications.
	 */
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServiceApplication.class, args);
	}

}

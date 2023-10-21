package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
	@Bean
	RouteLocator gatewayRoute(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("cartRoute", routeSpec -> routeSpec
						.path("/shoppingcart/**")
						.filters(f-> f.addRequestHeader("X-RESPONSE-Header", "SCS"))
						.uri("lb://cart-service")
						).build();
	}

}

package com.example.CardServiceAPIGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CardServiceApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardServiceApiGatewayApplication.class, args);
	}

}

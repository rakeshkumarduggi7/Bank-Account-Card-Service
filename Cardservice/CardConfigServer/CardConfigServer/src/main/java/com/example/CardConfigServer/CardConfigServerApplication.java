package com.example.CardConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CardConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardConfigServerApplication.class, args);
	}

}

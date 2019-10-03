package com.example.yarnapplication.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.yarn.client.YarnClient;

@EnableAutoConfiguration
public class YarnClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(YarnClientApplication.class, args).getBean(YarnClient.class).submitApplication();
	}

	
}

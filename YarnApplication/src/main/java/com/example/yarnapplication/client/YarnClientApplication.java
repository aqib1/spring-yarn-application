package com.example.yarnapplication.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.yarn.client.YarnClient;
import com.example.yarnapplication.container.ContainerApplication;

@Configuration
@EnableAutoConfiguration
public class YarnClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(YarnClientApplication.class, args).getBean(YarnClient.class).submitApplication();
	}

	@Bean
	public ContainerApplication getContainerApplication() {
		return new ContainerApplication();
	}
}

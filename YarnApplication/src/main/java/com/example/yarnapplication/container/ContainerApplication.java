package com.example.yarnapplication.container;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.yarn.client.YarnClient;

import com.example.yarnapplication.helpers.FindHelper;

@Configuration
@EnableAutoConfiguration
public class ContainerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ContainerApplication.class, args).getBean(YarnClient.class).submitApplication();
	}

	@Bean
	public HDFSFileInfoContainer helloPojo() {
		return new HDFSFileInfoContainer();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running Jobs against args ["+Arrays.toString(args)+"]");
		FindHelper.getInstance().input(args);
	}
}

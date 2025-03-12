package com.byteforce.healthhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HealthhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthhubApplication.class, args);
	}

}


package com.mymax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MymaxProspectingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MymaxProspectingServiceApplication.class, args);
	}

}

package com.poc.healthinsurancesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HealthinsurancesystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthinsurancesystemApplication.class, args);
	}

}

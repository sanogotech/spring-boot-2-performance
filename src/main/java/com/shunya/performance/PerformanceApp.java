package com.shunya.performance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PerformanceApp {

	public static void main(String[] args) {
		SpringApplication.run(PerformanceApp.class, args);
	}
}

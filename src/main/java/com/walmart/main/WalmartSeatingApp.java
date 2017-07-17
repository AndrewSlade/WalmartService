package com.walmart.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = { "com.walmart.main" })
@EnableScheduling
public class WalmartSeatingApp {

	public static void main(String[] args) {
		SpringApplication.run(WalmartSeatingApp.class, args);
	}
}
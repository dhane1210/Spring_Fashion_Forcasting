package com.backend.fashion_trend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		System.out.println("--- SPRING BOOT BACKEND IS RUNNING ON PORT 8080 ---");
	}

}

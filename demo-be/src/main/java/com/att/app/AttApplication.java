package com.att.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AttApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AttApplication.class, args);
	}
}

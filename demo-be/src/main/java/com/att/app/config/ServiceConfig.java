package com.att.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.att.app.service", "com.att.app.mapper" })
public class ServiceConfig {
	// service configuration here
}

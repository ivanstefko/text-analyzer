package com.att.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import(ServiceConfig.class)
@ComponentScan(basePackages = { "com.att.app.exception" })
//@EnableConfigurationProperties(value = { AppPropertyHolder.class })
public class RootConfig {
	
	/** The environemnt properties. */
	@Autowired
	private Environment env;
}

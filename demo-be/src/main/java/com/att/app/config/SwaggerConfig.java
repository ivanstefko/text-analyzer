package com.att.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * The {@link SwaggerConfig} class is config class for Swagger UI REST API.
 *
 * @author Ivan Stefko
 *
 * http://<host>/api/swagger-ui.html
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String API_INFO_DESCRIPTION = "AT&T DEMO API";

	/** The api info title. */
	private final String API_INFO_TITLE = "AT&T DEMO API";

	/** The api info version. */
	private final String API_INFO_VERSION = "1.0";

	/**
	 * Swagger spring mvc plugin.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket swaggerBean() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.att.app.controller"))
				.paths(PathSelectors.regex("/.*")).build()
				.apiInfo(new ApiInfo(
						API_INFO_TITLE,
						API_INFO_DESCRIPTION,
						API_INFO_VERSION,
						"",
						getContactInfo(),
						"",
						"",
						new ArrayList<>()
				));
	}

	/**
	 * Gets Contact information.
	 *
	 * @return Contact info
	 */
	private Contact getContactInfo() {
		return new Contact("Test Exam / AT&T", "", "ivan.stefko@gmail.com");
	}

}

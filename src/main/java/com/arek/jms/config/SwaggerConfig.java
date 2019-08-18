package com.arek.jms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket jmsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(any())
				.build()
				.apiInfo(metaApiInfo());
	}

	private ApiInfo metaApiInfo() {
		return new ApiInfoBuilder()
				.title("JMS API")
				.description("jms api using activemq on an external broker")
				.license("Apache")
				.licenseUrl("license.url")
				.termsOfServiceUrl("Terms of Service")
				.version("1.0.0")
				.contact(new Contact("Arkadiusz Ceglowski", "http://github.com/arekceg", "email"))
				.build();
	}

}

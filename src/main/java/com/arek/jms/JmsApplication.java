package com.arek.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableJpaRepositories
public class JmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsApplication.class, args);
	}

}

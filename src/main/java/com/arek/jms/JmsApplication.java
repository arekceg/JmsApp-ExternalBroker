package com.arek.jms;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.Queue;

@SpringBootApplication
@EnableJms
@EnableJpaRepositories
public class JmsApplication {


	public static void main(String[] args) {
		SpringApplication.run(JmsApplication.class, args);
	}

}

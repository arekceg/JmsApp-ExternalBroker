package com.arek.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.Queue;

@Configuration
public class JmsConfig {

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		return new ActiveMQConnectionFactory();
	}

	@Bean
	public DefaultJmsListenerContainerFactory queueContainerFactory() {
		DefaultJmsListenerContainerFactory queueContainterFactory =
				new DefaultJmsListenerContainerFactory();
		queueContainterFactory.setConnectionFactory(activeMQConnectionFactory());
		queueContainterFactory.setPubSubDomain(false);
		return queueContainterFactory;
	}

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("queue");
	}
}

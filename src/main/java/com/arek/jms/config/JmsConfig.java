package com.arek.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@Configuration
public class JmsConfig {

	public static final String queueFactory = "queueContainerFactory";
	public static final String topicFactory = "topicContainerFactory";

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory =
				new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL("tcp://activemq:61616");
		return activeMQConnectionFactory;
	}

	@Bean
	public DefaultJmsListenerContainerFactory topicContainerFactory() {
		DefaultJmsListenerContainerFactory factory =
				new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(activeMQConnectionFactory());
		factory.setPubSubDomain(true);
		return factory;
	}

	@Bean
	public DefaultJmsListenerContainerFactory queueContainerFactory() {
		DefaultJmsListenerContainerFactory factory =
				new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(activeMQConnectionFactory());
		factory.setPubSubDomain(false);
		return factory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		return new JmsTemplate(activeMQConnectionFactory());
	}

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("queue");
	}

}

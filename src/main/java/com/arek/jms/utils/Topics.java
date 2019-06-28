package com.arek.jms.utils;

import org.apache.activemq.command.ActiveMQTopic;

public enum Topics {
	A ("A"),
	BC ("BC");

	final String name;

	Topics(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public javax.jms.Topic getTopic(){
		return new ActiveMQTopic(this.name);
	}
}

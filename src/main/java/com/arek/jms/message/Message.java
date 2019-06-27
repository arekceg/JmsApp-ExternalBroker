package com.arek.jms.message;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Message {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long id;

	String content;
	String topic;
	String listenerName;

	public Message() {
	}

	public Message(String content, String topic, String listenerName) {
		this.content = content;
		this.topic = topic;
		this.listenerName = listenerName;
	}

	public static MessageBuilder builder() {
		return new MessageBuilder();
	}

	@Override
	public String toString() {
		return "Message{" +
				"id=" + id +
				", content='" + content + '\'' +
				", topic='" + topic + '\'' +
				", listenerName='" + listenerName + '\'' +
				'}';
	}

	public static class MessageBuilder {
		private String content;
		private String topic;
		private String listenerName;

		MessageBuilder() {
		}


		public MessageBuilder content(String content) {
			this.content = content;
			return this;
		}

		public MessageBuilder topic(String topic) {
			this.topic = topic;
			return this;
		}

		public MessageBuilder listenerName(String listenerName) {
			this.listenerName = listenerName;
			return this;
		}

		public Message build() {
			return new Message(content, topic, listenerName);
		}

		public String toString() {
			return "Message.MessageBuilder(content=" + this.content + ", topic=" + this.topic + ", listenerName=" + this.listenerName + ")";
		}
	}
}

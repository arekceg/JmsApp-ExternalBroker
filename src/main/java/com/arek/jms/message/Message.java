package com.arek.jms.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Message {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	Long id;

	@ApiModelProperty(allowableValues = "String", notes = "Message content")
	String content;
	@ApiModelProperty(notes = "Message source: topic or queue")
	String source;
	String listenerName;

	public Message(String content, String source, String listenerName) {
		this.content = content;
		this.source = source;
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
				", source='" + source + '\'' +
				", listenerName='" + listenerName + '\'' +
				'}';
	}

	public static class MessageBuilder {
		private String content;
		private String source;
		private String listenerName;

		MessageBuilder() {
		}


		public MessageBuilder content(String content) {
			this.content = content;
			return this;
		}

		public MessageBuilder source(String topic) {
			this.source = topic;
			return this;
		}

		public MessageBuilder listenerName(String listenerName) {
			this.listenerName = listenerName;
			return this;
		}

		public Message build() {
			return new Message(content, source, listenerName);
		}

		public String toString() {
			return "Message.MessageBuilder(content=" + this.content + ", source=" + this.source + ", listenerName=" + this.listenerName + ")";
		}
	}
}

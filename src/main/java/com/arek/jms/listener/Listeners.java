package com.arek.jms.listener;

import com.arek.jms.message.Message;
import com.arek.jms.message.MessageService;
import com.arek.jms.utils.ListenerName;
import com.arek.jms.utils.TopicName;
import com.arek.jms.utils.TopicNames;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class Listeners {

	//fields

	private MessageService messageService;

	//queue listeners

	@JmsListener(destination = "queue", containerFactory = "queueContainerFactory")
	public void JmsQueueListenerA(String messageContent) {
		Message message = Message.builder()
				.content(messageContent)
				.listenerName("queueListenerA")
				.source("queue")
				.build();
		messageService.saveMessage(message);
		log.info("== QUEUE LISTENER A: Message saved : " + message);
	}

	@JmsListener(destination = "queue", containerFactory = "queueContainerFactory")
	public void JmsQueueListenerB(String messageContent) {
		Message message = Message.builder()
				.content(messageContent)
				.listenerName("queueListenerB")
				.source("queue")
				.build();
		messageService.saveMessage(message);
		log.info("== QUEUE LISTENER B: Message saved : " + message);
	}

	//topic listeners

	@JmsListener(destination = TopicNames.A)
	public void jmsListenerA(String messageContent) {
		Message message = Message.builder()
				.content(messageContent)
				.listenerName(ListenerName.A.toString())
				.source(TopicName.A.toString())
				.build();
		messageService.saveMessage(message);
		log.info("== LISTENER A: Message saved : " + message);
	}

	@JmsListener(destination = TopicNames.BC)
	public void jmsListenerB(String messageContent) {
		Message message = Message.builder()
				.content(messageContent)
				.listenerName(ListenerName.B.toString())
				.source(TopicName.BC.toString())
				.build();
		messageService.saveMessage(message);
		log.info("== LISTENER B: Message saved : " + message);
	}

	@JmsListener(destination = TopicNames.BC)
	public void jmsListenerC(String messageContent) {
		Message message = Message.builder()
				.content(messageContent)
				.listenerName(ListenerName.C.toString())
				.source(TopicName.BC.toString())
				.build();
		messageService.saveMessage(message);
		log.info("== LISTENER C: Message saved : " + message);
	}

}

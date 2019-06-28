package com.arek.jms.controller;

import com.arek.jms.message.Message;
import com.arek.jms.message.MessageService;
import com.arek.jms.utils.TopicName;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

	private JmsTemplate jmsTemplate;
	private MessageService messageService;
	private Queue queue;

	@GetMapping("all")
	public List<Message> getAllMsg() {
		return messageService.getAllMessages();
	}

	@PostMapping("/send/topic/{topic}/{msg}")
	public ResponseEntity<String> sendMessage(@PathVariable String topic,
	                                          @PathVariable String msg) {
		TopicName foundTopicName = Arrays.stream(TopicName.values())
				.filter(topicName -> topicName.toString().equalsIgnoreCase(topic))
				.findFirst().orElse(null);

		if (foundTopicName == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		jmsTemplate.convertAndSend(topic.toUpperCase(), msg);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

	@PostMapping("/send/queue/{msg}")
	public ResponseEntity<String> sendToQueue(@PathVariable String msg){
		jmsTemplate.convertAndSend(queue,msg);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
}

package com.arek.jms.controller;

import com.arek.jms.message.Message;
import com.arek.jms.message.MessageService;
import com.arek.jms.utils.Topics;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

	@PostMapping("/send/topic/{topicName}/{msg}")
	public ResponseEntity<String> sendToTopic(@PathVariable String topicName,
	                                          @PathVariable String msg) {
		Optional<Topic> optTopic = Arrays.stream(Topics.values())
				.filter(t -> t.getName().equalsIgnoreCase(topicName))
				.map(Topics::getTopic)
				.findFirst();

		if (!optTopic.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		jmsTemplate.convertAndSend(optTopic.get(), msg);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

	@PostMapping("/send/queue/{msg}")
	public ResponseEntity<String> sendToQueue(@PathVariable String msg) {
		jmsTemplate.convertAndSend(queue, msg);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
}

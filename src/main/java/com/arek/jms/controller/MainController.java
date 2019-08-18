package com.arek.jms.controller;

import com.arek.jms.message.Message;
import com.arek.jms.message.MessageService;
import com.arek.jms.utils.Topics;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Main controller for the API")
public class MainController {

	private JmsTemplate jmsTemplate;
	private MessageService messageService;
	private Queue queue;

	@ApiOperation(value = "Returns all sent messages from internal h2 database")
	@GetMapping("all")
	public List<Message> getAllMsg() {
		return messageService.getAllMessages();
	}

	@ApiOperation(value = "Sends {msg} to selected topic", notes = "Available topic names: A, BC")
	@ApiResponses(value = @ApiResponse(code = 404, message = "Topic not found, wrong topic name"))

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

	@ApiOperation(value = "Sends {msg} to the default queue")
	@PostMapping("/send/queue/{msg}")
	public ResponseEntity<String> sendToQueue(@PathVariable String msg) {
		jmsTemplate.convertAndSend(queue, msg);
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
}

package com.arek.jms.message;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

	private MessageRepository messageRepository;

	public Message saveMessage(Message message){
		return messageRepository.save(message);
	}

	public List<Message> getAllMessages(){
		return messageRepository.findAll();
	}

}

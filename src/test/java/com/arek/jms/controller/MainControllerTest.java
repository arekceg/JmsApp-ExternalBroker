package com.arek.jms.controller;

import com.arek.jms.utils.Topics;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import javax.jms.Topic;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class MainControllerTest {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@InjectMocks
	private MainController mainController;

	@Mock
	private JmsTemplate jmsTemplate;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() {

		this.mockMvc = MockMvcBuilders
				.standaloneSetup(mainController)
				.build();
	}

	@Test
	public void sendToTopicTest_shouldReturnTopicBasedOnPathVariable() throws Exception {
		ArgumentCaptor<Topic> topicArgumentCaptor = ArgumentCaptor.forClass(Topic.class);
		//given
		willDoNothing().given(jmsTemplate).convertAndSend(any(Topic.class), anyString());

		//when
		mockMvc.perform(post("/send/topic/{topic}/{msg}", "A", "TestMSG"))
				.andExpect(status().isCreated())
				.andDo(print());

		//then
		then(jmsTemplate).should().convertAndSend(topicArgumentCaptor.capture(), anyString());
		assertEquals(topicArgumentCaptor.getValue(), Topics.A.getTopic());
	}

	@Test
	public void sendToTopicTest_wrongTopic_shouldReturn404() throws Exception {
		//given
		willDoNothing().given(jmsTemplate).convertAndSend(any(Topic.class), anyString());

		//when
		mockMvc.perform(post("/send/topic/{topic}/{msg}", "D", "TestMSG"))
				.andExpect(status().isNotFound())
				.andDo(print());

		//then
		then(jmsTemplate).should(never()).convertAndSend(any(Topic.class), anyString());
	}

}
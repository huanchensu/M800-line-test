package com.m800.line.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.m800.line.chat.model.dto.LineMessageDTO;
import com.m800.line.chat.repository.LineMessageRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@LineMessageHandler
@Slf4j
public class LineMessageListener {

	@Autowired
	LineMessageRepository lineMessageRepository;

	@EventMapping
	public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		log.info("event: " + event);
		String userId = event.getSource().getUserId();
		final String originalMessageText = event.getMessage().getText();
		LineMessageDTO message = LineMessageDTO.builder().id(event.getReplyToken()).message(originalMessageText)
				.userId(userId).build();
		lineMessageRepository.save(message);
		return new TextMessage(originalMessageText);
	}

}

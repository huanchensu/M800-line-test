package com.m800.line.chat.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.m800.line.chat.model.dto.LineMessageDTO;
import com.m800.line.chat.repository.LineMessageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LineService {

	private final LineMessagingClient lineMessagingClient;
	private final LineMessageRepository lineMessageRepository;

	public void pushMessage(String userId, String message) {
		List<Message> messages = new ArrayList<Message>();
		messages.add(new TextMessage(message));
		lineMessagingClient.pushMessage(new PushMessage(userId, messages));
		log.info("Push Message Complete, user:{} message:{}", userId, message);
	}

	public List<String> getUserChatRecord(String userId) {
		List<LineMessageDTO> messages = lineMessageRepository.findByUserId(userId);
		if (messages == null) {
			return Collections.emptyList();
		}
		return messages.stream().map(i -> i.getMessage()).collect(Collectors.toList());
	}

}
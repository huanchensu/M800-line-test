package com.m800.line.chat.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m800.line.chat.model.request.SendMessageRequest;
import com.m800.line.chat.service.LineService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Slf4j
public class LineUserInteractionController {

	private final LineService lineService;

	@GetMapping("chatRecord")
	public ResponseEntity<?> getUserChatRecord(String userId) {
		log.info("[getUserChatRecord] userId:{}", userId);
		if (StringUtils.isBlank(userId)) {
			return new ResponseEntity<String>("userId is null", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<String>>(lineService.getUserChatRecord(userId), HttpStatus.OK);
	}

	@PostMapping("sendMessage")
	public ResponseEntity<String> sendMessage(@Validated SendMessageRequest request) {
		try {
			lineService.pushMessage(request.getUserId(), request.getMessage());
		} catch (Exception e) {
			log.error("[sendMessage] fail message:{}", e.getMessage());
			return new ResponseEntity<String>("Fail", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

}

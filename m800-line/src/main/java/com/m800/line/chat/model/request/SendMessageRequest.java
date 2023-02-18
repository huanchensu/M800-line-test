package com.m800.line.chat.model.request;

import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class SendMessageRequest {

	@NonNull
	private String userId;

	@NonNull
	private String message;
}

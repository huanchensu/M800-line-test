package com.m800.line.chat.model.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Builder;
import lombok.Data;

@Document(collection = "LineMessage")
@Data
@Builder
public class LineMessageDTO {

	@Id
	private String id;
	@Field("user_id")
	private String userId;
	@Field("message")
	private String message;

}

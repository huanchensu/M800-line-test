package com.m800.line.chat.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.m800.line.chat.model.dto.LineMessageDTO;

public interface LineMessageRepository extends MongoRepository<LineMessageDTO, String> {

	List<LineMessageDTO> findByUserId(String userId);

}

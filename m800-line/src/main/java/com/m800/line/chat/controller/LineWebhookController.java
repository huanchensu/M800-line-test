package com.m800.line.chat.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineWebhookController {

	 @RequestMapping("/callback")
	    public void callback(@RequestBody String message){
		 	System.out.println(message);
	    }
}

package com.chatbook.loadbalance.contol;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatbook-app")
public class ChatbookController {

	@Value("${server.port}")
	private String port;
	
	@GetMapping("/chat")
	public String chatNow() {
		return "application up on port : "+port;
	}

}

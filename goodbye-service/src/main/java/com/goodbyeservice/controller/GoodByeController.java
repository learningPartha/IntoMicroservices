package com.goodbyeservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodByeController {
	
	@RequestMapping("/")
	public String goodBye() {
		return "Goodbye !";
	}

}

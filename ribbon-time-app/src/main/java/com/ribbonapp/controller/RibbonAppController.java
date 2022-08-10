package com.ribbonapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonAppController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/")
	public String getTime() {
		return restTemplate.getForEntity("http://time-service", String.class).getBody();
	}

}

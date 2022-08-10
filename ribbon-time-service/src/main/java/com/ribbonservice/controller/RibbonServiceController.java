package com.ribbonservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonServiceController {

	@Value("${server.port}")
	private int port;
	
	@GetMapping("/")
	public String getTime() {
		return "Current Time is "+new Date().toString()
			+" answered by service running on "+port+" !";
	}
	
}

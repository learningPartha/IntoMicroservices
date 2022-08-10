package com.timeservice.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeServiceController {
	
	@GetMapping("/time")
	public String getTime() {
		return new Date().toString();
	}
}

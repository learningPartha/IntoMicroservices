package com.timeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.timeapp.service.TimeAppService;

@RestController
public class TimeAppController {
	
	@Autowired
	private TimeAppService timeService;
	
	@GetMapping("/current/time")
	public String getTimeService() {
		return "Current Time is "+timeService.getTime();
	}
}

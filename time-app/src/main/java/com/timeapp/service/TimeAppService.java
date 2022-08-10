package com.timeapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class TimeAppService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="failHandle")
	public String getTime() {
		return restTemplate.getForEntity("http://time-service/time", String.class).getBody();
	}
	
	public String failHandle() {
		return "unknown";
	}
}

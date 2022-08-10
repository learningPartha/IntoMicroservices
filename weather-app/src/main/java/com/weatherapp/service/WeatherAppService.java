package com.weatherapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class WeatherAppService {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="failHandle")
	public String getWeather() {
		return restTemplate.getForEntity("http://weather-service/weather", String.class).getBody();
	}
	
	public String failHandle() {
		return "unknown";
	}
}

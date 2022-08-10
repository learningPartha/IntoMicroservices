package com.weatherservice.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherServiceController {
	
	private String[] weather = new String[] {"sunny","cloudy","rainy","windy"};
	
	@GetMapping("/weather")
	public String getWeather() {
		int rand = ThreadLocalRandom.current().nextInt(0,3);
		return weather[rand];
	}
	
}

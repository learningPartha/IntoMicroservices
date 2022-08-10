package com.weatherapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weatherapp.service.WeatherAppService;

@RestController
public class WeatherAppController {

	@Autowired
	private WeatherAppService weatherService;
	
	@GetMapping("/current/weather")
	public String getWeatherService() {
		return "Current weather is "+weatherService.getWeather();
	}
	
}

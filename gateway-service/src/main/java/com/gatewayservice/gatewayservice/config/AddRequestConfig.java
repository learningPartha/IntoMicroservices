package com.gatewayservice.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gatewayservice.gatewayservice.filter.AddRequestFilter;

@Configuration
public class AddRequestConfig {
	
	@Bean
	public AddRequestFilter addRequestFilter() {
		return new AddRequestFilter();
	}

}

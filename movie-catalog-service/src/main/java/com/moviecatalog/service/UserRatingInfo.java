package com.moviecatalog.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moviecatalog.model.Rating;
import com.moviecatalog.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserRatingInfo {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackUserRating", 
		threadPoolKey = "userRatingPool",
		threadPoolProperties = {// bulk head pattern to compartmentalize thread pool for each service
			@HystrixProperty(name="coreSize",value="20"),
			@HystrixProperty(name="maxQueueSize",value="10")
		},
		commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000") 
		 })
	public UserRating getUserRating(String userId) {// get user rating data
		UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId,
				UserRating.class);
		return ratings;
	}

	public UserRating getFallbackUserRating(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setUserRatings(Arrays.asList(new Rating("0", 0)));
		return userRating;
	}
}

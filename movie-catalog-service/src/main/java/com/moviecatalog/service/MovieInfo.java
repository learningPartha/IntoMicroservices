package com.moviecatalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.moviecatalog.model.CatalogItem;
import com.moviecatalog.model.Movie;
import com.moviecatalog.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
		threadPoolKey = "movieInfoPool",
		threadPoolProperties = {// bulk head pattern to compartmentalize thread pool for each service
		    @HystrixProperty(name="coreSize",value="20"),
		    @HystrixProperty(name="maxQueueSize",value="10")
		},
		commandProperties = {//circuit breaker parameters
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000") 
		  })
	public CatalogItem getCatalogItem(Rating rating) {// get movie info
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

		// Movie movie =
		// restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),
		// Movie.class);
		// return new CatalogItem(movie.getMovieName(), "Epic Fantasy",
		// rating.getRating());
		/*
		 * Movie[] movie =webClientBuilder.build().get()
		 * .uri("http://localhost:8082/movies/"+rating.getMovieId())
		 * .retrieve().bodyToMono(Movie[].class) .block();
		 */

		return new CatalogItem(movie.getMovieName(), movie.getMovieDescription(), rating.getRating());
	}

	// fallback method sending default values
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		CatalogItem catalogItem = new CatalogItem();
		catalogItem.setMovieName("Movie not found");
		catalogItem.setMovieDesc("Movie not found");
		catalogItem.setRating(rating.getRating());
		return catalogItem;
	}
}

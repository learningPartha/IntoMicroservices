package com.moviecatalog.resource;

import java.util.Arrays;
//import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import com.moviecatalog.model.CatalogItem;
import com.moviecatalog.model.Movie;
import com.moviecatalog.model.Rating;
//import com.moviecatalog.model.Rating;
import com.moviecatalog.model.UserRating;
import com.moviecatalog.service.MovieInfo;
import com.moviecatalog.service.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	/*
	 * @Autowired private RestTemplate restTemplate;
	 */

	@Autowired
	MovieInfo movieInfo;

	@Autowired
	UserRatingInfo userRatingInfo;

	/*
	 * @Autowired private WebClient.Builder webClientBuilder;
	 */

	/*
	 * @Autowired private DiscoveryClient discoveryClient;
	 */

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		// get all movie id
		// List<Rating> ratings = Arrays.asList(new Rating("rowl", 5), new
		// Rating("tolk", 9));

		UserRating ratings = userRatingInfo.getUserRating(userId);

		return ratings.getUserRatings().stream().map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());

		// return Collections.singletonList(new CatalogItem("LOTR", "Epic Fantasy", 9));
	}

}

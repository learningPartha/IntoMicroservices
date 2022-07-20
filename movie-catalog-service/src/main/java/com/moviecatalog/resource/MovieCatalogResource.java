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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

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

		UserRating ratings = getUserRating(userId);

		return ratings.getUserRatings().stream()
				.map(rating -> getCatalogItem(rating)).collect(Collectors.toList());

		// return Collections.singletonList(new CatalogItem("LOTR", "Epic Fantasy", 9));
	}

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	private CatalogItem getCatalogItem(Rating rating) {// get movie info
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), 
				Movie.class);

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

	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	private UserRating getUserRating(String userId) {// get user rating data
		UserRating ratings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/" + userId,
				UserRating.class);
		return ratings;
	}

	// fallback method sending default values
	private CatalogItem getFallbackCatalogItem(Rating rating) {
		CatalogItem catalogItem = new CatalogItem();
		catalogItem.setMovieName("Movie not found");
		catalogItem.setMovieDesc("Movie not found");
		catalogItem.setRating(rating.getRating());
		return catalogItem;
	}

	private UserRating getFallbackUserRating(String userId) {
		UserRating userRating = new UserRating();
		userRating.setUserId(userId);
		userRating.setUserRatings(Arrays.asList(new Rating("0", 0)));
		return userRating;
	}

}

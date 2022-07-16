package com.moviecatalog.resource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import com.moviecatalog.model.CatalogItem;
import com.moviecatalog.model.Movie;
import com.moviecatalog.model.Rating;
import com.moviecatalog.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	/*
	 * @Autowired private WebClient.Builder webClientBuilder;
	 */

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		// get all movie id
		//List<Rating> ratings = Arrays.asList(new Rating("rowl", 5), new Rating("tolk", 9));
		
		UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);
			

		return ratings.getUserRatings().stream().map(rating -> {
			// Movie movie =
			// restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(),
			// Movie.class);
			// return new CatalogItem(movie.getMovieName(), "Epic Fantasy",
			// rating.getRating());
			Movie[] movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(),
					Movie[].class);

			/*
			 * Movie[] movie =webClientBuilder.build().get()
			 * .uri("http://localhost:8082/movies/"+rating.getMovieId())
			 * .retrieve().bodyToMono(Movie[].class) .block();
			 */

			return new CatalogItem(movie[0].getMovieName(), "Epic Fantasy", rating.getRating());

		}).collect(Collectors.toList());

		// for each movie id , call movie info service and get details

		// get the final result

		// return Collections.singletonList(new CatalogItem("LOTR", "Epic Fantasy", 9));
	}

}

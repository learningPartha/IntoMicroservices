package com.movieinfo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.movieinfo.model.Movie;
import com.movieinfo.model.MovieSummary;


@RestController
@RequestMapping("/movies")
public class MovieInfoResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.key}")
	private String apiKey;

	/*@RequestMapping("/{movieId}")
	public List<Movie> getMovieInfo(@PathVariable("movieId") String movieId){
		return Collections.singletonList(
				new Movie(movieId, "LOTR")
				);
	}*/
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId){
		
		MovieSummary movieSummary =
				restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, 
						MovieSummary.class);
		
		return new Movie(movieId, movieSummary.getTitle(),movieSummary.getOverview());
		
	}
	
}

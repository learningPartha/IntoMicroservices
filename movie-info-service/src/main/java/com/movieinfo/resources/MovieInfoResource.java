package com.movieinfo.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieinfo.model.Movie;


@RestController
@RequestMapping("/movies")
public class MovieInfoResource {

	@RequestMapping("/{movieId}")
	public List<Movie> getMovieInfo(@PathVariable("movieId") String movieId){
		return Collections.singletonList(
				new Movie(movieId, "LOTR")
				);
	}
	
}

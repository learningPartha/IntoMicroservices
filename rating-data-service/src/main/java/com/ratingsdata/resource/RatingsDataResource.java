package com.ratingsdata.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingsdata.model.Rating;
import com.ratingsdata.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 9);
    }
    
    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
    	List<Rating> ratings = Arrays.asList(new Rating("rowl", 5), new Rating("tolk", 9));
    	UserRating userRating = new UserRating();
    	userRating.setUserRatings(ratings);
    	return userRating;
    }
}

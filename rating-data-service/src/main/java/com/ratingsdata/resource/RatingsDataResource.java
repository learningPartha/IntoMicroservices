package com.ratingsdata.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingsdata.model.DbSetting;
import com.ratingsdata.model.Rating;
import com.ratingsdata.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
@RefreshScope
public class RatingsDataResource {
	
	@Value("${movieRating.value}")
	private String rating ;
	
	@Value("${movieRating.list}")
	private List<String> ratingList ;
	
	//@Value("#{${db.connection}}")
	//private Map<String,String> dbValues;
	
	@Autowired
	private DbSetting dbSetting;
	
	@Autowired
	private Environment env;

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, Integer.parseInt(rating));
    }
    
    @RequestMapping("/list/{movieId}")
    public String getRatingList(@PathVariable("movieId") String movieId) {
        //return "For movie Id"+movieId+"List is "+ratingList+" database pool"+dbValues;
        return "For movie Id"+movieId+"List is "+ratingList+" database pool"
        		+dbSetting.getConnection()+dbSetting.getHost()+dbSetting.getPort();
    }
    
    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
    	UserRating userRating = new UserRating();
    	userRating.initData(userId);
    	return userRating;
    }
    
    @GetMapping("/envdetails")
    public String getEnvDetail() {
    	return env.toString();
    }
}

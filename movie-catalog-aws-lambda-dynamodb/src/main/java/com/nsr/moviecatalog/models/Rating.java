package com.nsr.moviecatalog.models;

import java.util.List;
import java.util.Map;

public class Rating {


	private String userId;
	private List<Map<String,Integer>>  movieRatingList;

	public Rating() {}
	
	public Rating(String userId, List<Map<String, Integer>> movieRatingList) {
		super();
		this.userId = userId;
		this.movieRatingList = movieRatingList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Map<String, Integer>> getMovieRatingList() {
		return movieRatingList;
	}

	public void setMovieRatingList(List<Map<String, Integer>> movieRatingList) {
		this.movieRatingList = movieRatingList;
	}
	
	
	
	
}

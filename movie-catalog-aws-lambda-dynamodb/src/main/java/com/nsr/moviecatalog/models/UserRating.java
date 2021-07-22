package com.nsr.moviecatalog.models;

import java.util.List;
import java.util.Map;

public class UserRating {

    private List<Map<String,Integer>> userRatingList;

    public UserRating() {}
    
	public UserRating(List<Map<String, Integer>> userRatingList) {
		super();
		this.userRatingList = userRatingList;
	}

	public List<Map<String, Integer>> getUserRatingList() {
		return userRatingList;
	}

	public void setUserRatingList(List<Map<String, Integer>> userRatingList) {
		this.userRatingList = userRatingList;
	}
	
	

  }

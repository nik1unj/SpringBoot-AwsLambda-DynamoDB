package com.nsr.movierating;

import com.nsr.movierating.model.Rating;

public class Request {
	private String httpMethod;
	
	private String userId;

	private Rating rating;
	

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
	

	


}

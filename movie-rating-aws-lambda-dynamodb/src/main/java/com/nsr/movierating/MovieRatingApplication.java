package com.nsr.movierating;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.lambda.runtime.Context;
import com.nsr.movierating.model.Rating;

public class MovieRatingApplication {

	public Object handleRequest(Request request, Context context){
		
		System.out.println(request.getUserId());
		AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.standard().withRegion("your region").build();
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		
		Rating rating = null;
//		rating = mapper.load(Rating.class,request.getUserId());
//		if (rating == null) {
//		throw new ResourceNotFoundException("Resource Not Found "+request.getUserId());
//		}
//		return rating;
		
		
	if(request.getHttpMethod().equals("GET"))
	{
		rating = mapper.load(Rating.class,request.getUserId());
		if (rating == null) {
			throw new ResourceNotFoundException("Resource Not Found "+request.getUserId());
		}
		return rating;

	}
	else {
		rating = request.getRating();
		mapper.save(rating);
		return rating;

	}
	}
	
}

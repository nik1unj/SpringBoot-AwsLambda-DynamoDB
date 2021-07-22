package com.nsr.movieinfo;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.lambda.runtime.Context;
import com.nsr.movieinfo.configure.Request;
import com.nsr.movieinfo.model.Movie;


//@EnableEurekaClient
public class MovieInfoApplication {

	public Object handleRequest(Request request, Context context){
		AmazonDynamoDB client = AmazonDynamoDBAsyncClientBuilder.standard().withRegion("us-east-2").build();
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		
		Movie movie = null;
		movie = mapper.load(Movie.class,request.getMovieId());
		if(request.getHttpMethod().equals("GET"))
		{
			movie = mapper.load(Movie.class,request.getMovieId());
			if (movie == null) {
				throw new ResourceNotFoundException("Resource Not Found "+request.getMovieId());
			}
			return movie;
		
		}
		else {
			movie = request.getMovie();
			mapper.save(movie);
			return movie;
		
		}
	
	}
}


package com.nsr.moviecatalog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaAsyncClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nsr.moviecatalog.models.CatalogItem;
import com.nsr.moviecatalog.models.Movie;
import com.nsr.moviecatalog.models.Rating;
import com.nsr.moviecatalog.models.Request;

public class MovieCatalogApplication {

	//AWSLambda client = AWSLambdaClientBuilder.defaultClient();
	AWSLambda client = AWSLambdaAsyncClient.builder().withRegion("us-east-2").build();
	InvokeRequest invokeRequest = new InvokeRequest();

	public Object handleRequest(Request request, Context context)
			throws ParseException, JsonParseException, JsonMappingException, IOException {

		// AWSLambdaAsyncClient.builder().withRegion("us-east-2").build();
		String userId = request.getUserId();
		
//		AWSLambdaClient
		
		JSONObject payloadObject = new JSONObject();
		payloadObject.put("userId", userId);
		payloadObject.put("httpMethod", "GET");
		String payload = payloadObject.toString();

		invokeRequest.withFunctionName("arn:aws:lambda:us-east-2:342123803606:function:user-ratings")
				.withPayload(payload);

		InvokeResult invokeResult = client.invoke(invokeRequest);
		String result = new String(invokeResult.getPayload().array());
		
		ObjectMapper objectMapper = new ObjectMapper();
		Rating ratings = objectMapper.readValue(result, Rating.class);
		
		List<Map<String, Integer>> userRatingsMap = ratings.getMovieRatingList();

		List<CatalogItem> catalogItems = new ArrayList<CatalogItem>();
		userRatingsMap.forEach(userRating -> userRating.entrySet().forEach(movieMap -> {
			Movie movie = getMovieObject(movieMap);
			catalogItems.add(new CatalogItem(movie.getMovieName(), movie.getMovieDesc(), movieMap.getValue()));
		}));

		return catalogItems;

	}

	private Movie getMovieObject(Entry<String, Integer> movieMap) {
		
		// AWSLambda client = AWSLambdaClientBuilder.defaultClient();
		//AWSLambda client = AWSLambdaAsyncClient.builder().withRegion("us-east-2").build();
		
		JSONObject payloadObject = new JSONObject();
		payloadObject.put("movieId", movieMap.getKey());
		payloadObject.put("httpMethod", "GET");
		String payload = payloadObject.toString();
		
		//InvokeRequest invokeRequest = new InvokeRequest();
		invokeRequest.withFunctionName("arn:aws:lambda:us-east-2:342123803606:function:movie-info-service")
				.withPayload(payload);	

		InvokeResult invokeResult = client.invoke(invokeRequest);
		String ans = new String(invokeResult.getPayload().array());
		
		ObjectMapper objectMapper = new ObjectMapper();
		Movie movie = new Movie();
		try {
			movie = objectMapper.readValue(ans, Movie.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return movie;

	}

}

//if(request.getHttpMethod().equals("GET"))
//{
//	rating = mapper.load(Rating.class,request.getUserId());
//	if (rating == null) {
//		throw new ResourceNotFoundException("Resource Not Found "+request.getUserId());
//	}
//	return rating;
//	
//	
//	
//
//}
//else {
//	mapper.save(rating);
//	return rating;
//
//}
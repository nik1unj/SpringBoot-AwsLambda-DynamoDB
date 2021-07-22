package com.nsr.moviecatalog.models;

public class CatalogItem {

    private String movieId;
    private String movieName;
    private int rating;

    public CatalogItem(){}

    public CatalogItem(String movieId, String movieName, int rating) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.rating = rating;
    }

 

    public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

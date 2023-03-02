package com.rkbapps.neetflix.services;

import com.rkbapps.neetflix.models.MovieList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("popular")
    Call<MovieList> getPopularMovies(@Query("api_key") String apiKey);
}

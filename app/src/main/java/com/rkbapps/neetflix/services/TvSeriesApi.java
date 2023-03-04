package com.rkbapps.neetflix.services;

import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TvSeriesApi {

    @GET("trending/tv/day")
    Call<TvSeriesListModel> getTrendingSeries(@Query("api_key") String apiKey);

    @GET("tv/popular")
    Call<TvSeriesListModel> getPopularSeries(@Query("api_key") String apiKey);

    @GET("tv/top_rated")
    Call<TvSeriesListModel> getTopRatedSeries(@Query("api_key") String apiKey);

    @GET("tv/airing_today")
    Call<TvSeriesListModel> getAiringTodaySeries(@Query("api_key") String apiKey);

}


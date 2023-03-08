package com.rkbapps.neetflix.services;

import com.rkbapps.neetflix.models.Review.ReviewModel;
import com.rkbapps.neetflix.models.castandcrew.CreditsModel;
import com.rkbapps.neetflix.models.images.ImagesModel;
import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel;
import com.rkbapps.neetflix.models.tvseries.TvSeriesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    @GET("tv/{tv_id}")
    Call<TvSeriesModel> getSeriesDetails(@Path("tv_id") int id, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/reviews")
    Call<ReviewModel> getSeriesReview(@Path("tv_id") int id, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/credits")
    Call<CreditsModel> getSeriesCredits(@Path("tv_id") int id, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/similar")
    Call<TvSeriesListModel> getSimilarTvSeries(@Path("tv_id") int id, @Query("api_key") String apiKey);

    @GET("tv/{tv_id}/images")
    Call<ImagesModel> getSeriesImages(@Path("tv_id") int id, @Query("api_key") String apiKey);




}


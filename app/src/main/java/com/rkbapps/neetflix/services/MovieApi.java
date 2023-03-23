package com.rkbapps.neetflix.services;

import com.rkbapps.neetflix.models.Review.ReviewModel;
import com.rkbapps.neetflix.models.castandcrew.CreditsModel;
import com.rkbapps.neetflix.models.images.ImagesModel;
import com.rkbapps.neetflix.models.movies.MovieListModel;
import com.rkbapps.neetflix.models.movies.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    // to get popular movies
    @GET("movie/popular")
    Call<MovieListModel> getPopularMovies(@Query("api_key") String apiKey);

    //to get trending movies
    @GET("trending/movie/day")
    Call<MovieListModel> getTrendingMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieListModel> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/latest")
    Call<MovieListModel> getLatestMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MovieListModel> getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Call<MovieModel> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/credits")
    Call<CreditsModel> getMovieCredits(@Path("movie_id") int movieId, @Query("api_key") String apiKey);


    @GET("movie/{movie_id}/similar")
    Call<MovieListModel> getSimilarMovies(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews")
    Call<ReviewModel> getMovieReviews(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("language") String language);

    @GET("movie/{movie_id}/images")
    Call<ImagesModel> getMovieImages(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieListModel> getMovieSearchResult(@Query("api_key") String apiKey, @Query("query") String query, @Query("page") int page, @Query("include_adult") boolean include_adult);


}

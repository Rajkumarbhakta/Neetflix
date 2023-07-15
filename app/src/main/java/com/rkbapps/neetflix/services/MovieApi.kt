package com.rkbapps.neetflix.services

import com.rkbapps.neetflix.models.Review.ReviewModel
import com.rkbapps.neetflix.models.castandcrew.CreditsModel
import com.rkbapps.neetflix.models.images.ImagesModel
import com.rkbapps.neetflix.models.movies.MovieListModel
import com.rkbapps.neetflix.models.movies.MovieModel
import com.rkbapps.neetflix.models.videos.VideoModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    // to get popular movies
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieListModel>

    //to get trending movies
    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieListModel>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieListModel>

    @GET("movie/latest")
    suspend fun getLatestMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieListModel>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Response<MovieListModel>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieModel>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<CreditsModel>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String?
    ): Call<MovieListModel>

    @GET("movie/{movie_id}/reviews")
    fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String?,
        @Query("language") language: String?
    ): Call<ReviewModel>

    @GET("movie/{movie_id}/images")
    fun getMovieImages(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String?
    ): Call<ImagesModel>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String?
    ): Call<VideoModel>

    @GET("search/movie")
    fun getMovieSearchResult(
        @Query("api_key") apiKey: String?,
        @Query("query") query: String?,
        @Query("page") page: Int,
        @Query("include_adult") include_adult: Boolean
    ): Call<MovieListModel>
}
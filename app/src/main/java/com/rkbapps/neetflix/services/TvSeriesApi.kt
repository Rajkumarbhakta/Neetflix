package com.rkbapps.neetflix.services

import com.rkbapps.neetflix.models.Review.ReviewModel
import com.rkbapps.neetflix.models.castandcrew.CreditsModel
import com.rkbapps.neetflix.models.images.ImagesModel
import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel
import com.rkbapps.neetflix.models.tvseries.TvSeriesModel
import com.rkbapps.neetflix.models.tvseries.seasons.SeasonsDetails
import com.rkbapps.neetflix.models.videos.VideoModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvSeriesApi {
    @GET("trending/tv/day")
    suspend fun getTrendingSeries(
        @Query("api_key") apiKey: String = ApiData.API_KEY,
        @Query("page") page: Int,
    ): Response<TvSeriesListModel>

    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("api_key") apiKey: String = ApiData.API_KEY,
        @Query("page") page: Int,
    ): Response<TvSeriesListModel>

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(
        @Query("api_key") apiKey: String = ApiData.API_KEY,
        @Query("page") page: Int,
    ): Response<TvSeriesListModel>

    @GET("tv/airing_today")
    suspend fun getAiringTodaySeries(
        @Query("api_key") apiKey: String = ApiData.API_KEY,
        @Query("page") page: Int,
    ): Response<TvSeriesListModel>

    @GET("tv/{tv_id}")
    suspend fun getSeriesDetails(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String = ApiData.API_KEY,
    ): Response<TvSeriesModel>

    @GET("tv/{tv_id}/reviews")
    fun getSeriesReview(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String = ApiData.API_KEY,
    ): Call<ReviewModel?>?

    @GET("tv/{tv_id}/credits")
    suspend fun getSeriesCredits(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String = ApiData.API_KEY,
    ): Response<CreditsModel>

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarTvSeries(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String = ApiData.API_KEY,
    ): Response<TvSeriesListModel>

    @GET("tv/{tv_id}/images")
    fun getSeriesImages(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String = ApiData.API_KEY,
    ): Call<ImagesModel?>?

    @GET("tv/{tv_id}/videos")
    fun getSeriesVideos(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String = ApiData.API_KEY,
    ): Call<VideoModel?>?

    @GET("search/tv")
    fun getSeriesSearchResult(
        @Query("api_key") apiKey: String = ApiData.API_KEY,
        @Query("query") query: String?,
        @Query("page") page: Int,
        @Query("include_adult") include_adult: Boolean,
    ): Call<TvSeriesListModel?>?

    @GET("tv/{tv_id}/season/{season_number}")
    fun getSeasonsDetails(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonsNumber: Int,
        @Query("api_key") apiKey: String = ApiData.API_KEY,
    ): Call<SeasonsDetails?>?
}
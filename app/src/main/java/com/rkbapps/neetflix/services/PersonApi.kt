package com.rkbapps.neetflix.services

import com.rkbapps.neetflix.models.person.ExternalIds
import com.rkbapps.neetflix.models.person.PersonDetails
import com.rkbapps.neetflix.models.person.images.PersonImageModel
import com.rkbapps.neetflix.models.person.movie.WorkForMovies
import com.rkbapps.neetflix.models.person.tvseries.WorkForSeries
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonApi {
    @GET("person/{person_id}/external_ids")
    fun getPersonExternalIds(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): Call<ExternalIds?>?

    @GET("person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): Response<PersonDetails>

    @GET("person/{person_id}/images")
    suspend fun getPersonImages(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): Response<PersonImageModel>

    @GET("person/{person_id}/tv_credits")
    suspend fun getPersonSeriesCredits(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): Response<WorkForSeries>

    @GET("person/{person_id}/movie_credits")
    suspend fun getPersonMovieCredits(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String
    ): Response<WorkForMovies>
}
package com.rkbapps.neetflix.services;

import com.rkbapps.neetflix.models.person.ExternalIds;
import com.rkbapps.neetflix.models.person.PersonDetails;
import com.rkbapps.neetflix.models.person.images.PersonImageModel;
import com.rkbapps.neetflix.models.person.movie.WorkForMovies;
import com.rkbapps.neetflix.models.person.tvseries.WorkForSeries;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PersonApi {

    @GET("person/{person_id}/external_ids")
    Call<ExternalIds> getPersonExternalIds(@Path("person_id") int personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}")
    Call<PersonDetails> getPersonDetails(@Path("person_id") int personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}/images")
    Call<PersonImageModel> getPersonImages(@Path("person_id") int personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}/tv_credits")
    Call<WorkForSeries> getPersonSeriesCredits(@Path("person_id") int personId, @Query("api_key") String apiKey);

    @GET("person/{person_id}/movie_credits")
    Call<WorkForMovies> getPersonMovieCredits(@Path("person_id") int personId, @Query("api_key") String apiKey);
}

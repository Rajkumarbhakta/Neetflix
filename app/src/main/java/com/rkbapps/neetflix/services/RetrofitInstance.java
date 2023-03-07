package com.rkbapps.neetflix.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiData.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build();


    private static MovieApi movieApi = null;
    private static TvSeriesApi tvSeriesApi = null;
    private static PersonApi personApi = null;


    public static MovieApi getMovieApi() {
        if (movieApi == null) {
            movieApi = retrofit.create(MovieApi.class);
        }
        return movieApi;
    }

    public static TvSeriesApi getTvSeriesApi() {
        if (tvSeriesApi == null) {
            tvSeriesApi = retrofit.create(TvSeriesApi.class);
        }
        return tvSeriesApi;
    }

    public static PersonApi getPersonApi() {
        if(personApi == null){
            personApi = retrofit.create(PersonApi.class);
        }
        return personApi;
    }
}

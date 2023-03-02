package com.rkbapps.neetflix.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiData.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build();
    private static MovieApi movieApi=null;

    public static MovieApi getMovieApi() {
        if(movieApi == null){
            movieApi = retrofit.create(MovieApi.class);
        }
        return movieApi;
    }
}

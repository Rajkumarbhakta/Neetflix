package com.rkbapps.neetflix.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiData.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    @JvmStatic
    var movieApi: MovieApi? = null
        get() {
            if (field == null) {
                field = retrofit.create(MovieApi::class.java)
            }
            return field
        }


    @JvmStatic
    var tvSeriesApi: TvSeriesApi? = null
        get() {
            if (field == null) {
                field = retrofit.create(TvSeriesApi::class.java)
            }
            return field
        }


    var personApi: PersonApi? = null
        get() {
            if (field == null) {
                field = retrofit.create(PersonApi::class.java)
            }
            return field
        }

}
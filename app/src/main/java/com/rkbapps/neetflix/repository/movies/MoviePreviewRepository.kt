package com.rkbapps.neetflix.repository.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.movies.MovieModel
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.Resource
import retrofit2.Response

class MoviePreviewRepository {

    private val movieLiveData = MutableLiveData<Resource<Response<MovieModel>>>()

    val movieData: LiveData<Resource<Response<MovieModel>>>
        get() = movieLiveData

    suspend fun loadMovieData(id: Int) {
        val response = RetrofitInstance.getMovieApi().getMovieDetails(id, ApiData.API_KEY)

        if (response.isSuccessful) {
            movieLiveData.postValue(Resource.Success(response))
        } else {
            movieLiveData.postValue(Resource.Error(response.message(), response))
        }
    }

}
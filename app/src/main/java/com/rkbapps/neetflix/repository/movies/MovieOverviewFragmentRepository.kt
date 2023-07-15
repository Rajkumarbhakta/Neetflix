package com.rkbapps.neetflix.repository.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.castandcrew.CreditsModel
import com.rkbapps.neetflix.models.movies.MovieListModel
import com.rkbapps.neetflix.models.movies.MovieModel
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.Resource
import retrofit2.Response

class MovieOverviewFragmentRepository {

    private val movieDetailsLiveData = MutableLiveData<Resource<Response<MovieModel>>>()
    val movieDetails: LiveData<Resource<Response<MovieModel>>>
        get() = movieDetailsLiveData

    suspend fun loadMovieDetails(id: Int) {
        val response = RetrofitInstance.getMovieApi().getMovieDetails(id, ApiData.API_KEY)
        if (response.isSuccessful) {
            movieDetailsLiveData.postValue(Resource.Success(response))
        } else {
            movieDetailsLiveData.postValue(Resource.Error(response.message(), response))
        }
    }

    private val creditsLiveData = MutableLiveData<Resource<Response<CreditsModel>>>()
    val creditsData: LiveData<Resource<Response<CreditsModel>>>
        get() = creditsLiveData

    suspend fun loadCredits(id: Int) {
        val response = RetrofitInstance.getMovieApi().getMovieCredits(id, ApiData.API_KEY)
        if (response.isSuccessful) {
            creditsLiveData.postValue(Resource.Success(response))
        } else {
            creditsLiveData.postValue(Resource.Error(response.message(), response))
        }
    }

    private val similarMoviesLiveData = MutableLiveData<Resource<Response<MovieListModel>>>()
    val similarMoviesData: LiveData<Resource<Response<MovieListModel>>>
        get() = similarMoviesLiveData

    suspend fun loadSimilarMovies(id: Int) {
        val response = RetrofitInstance.getMovieApi().getSimilarMovies(id, ApiData.API_KEY)
        if (response.isSuccessful) {
            similarMoviesLiveData.postValue(Resource.Success(response))
        } else {
            similarMoviesLiveData.postValue(Resource.Error(response.message(), response))
        }
    }


}
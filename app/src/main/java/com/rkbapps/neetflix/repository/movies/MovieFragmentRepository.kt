package com.rkbapps.neetflix.repository.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.movies.MovieListModel
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.Resource
import retrofit2.Response

class MovieFragmentRepository {


//Popular Movies

    private val popularMoviesLiveData = MutableLiveData<Resource<Response<MovieListModel>>>()
    val popularMovies: LiveData<Resource<Response<MovieListModel>>>
        get() = popularMoviesLiveData

    suspend fun loadPopularMovies(index: Int) {
        try {
            val movieApi = RetrofitInstance.movieApi
            val response = movieApi!!.getPopularMovies(ApiData.API_KEY, index)
            if (response.isSuccessful) {
                popularMoviesLiveData.postValue(Resource.Success(response))
            } else {
                popularMoviesLiveData.postValue(Resource.Error(response.message().toString(), response))
            }
        } catch (e: Exception) {
            popularMoviesLiveData.postValue(Resource.Error(e.localizedMessage?:"Something went wrong."))
        }
    }

//Trending Movies

    private val trendingMoviesLiveData = MutableLiveData<Resource<Response<MovieListModel>>>()
    val trendingMovies: LiveData<Resource<Response<MovieListModel>>>
        get() = trendingMoviesLiveData

    suspend fun loadTrendingMovies(index: Int) {
        try {
            val movieApi = RetrofitInstance.movieApi

            val response = movieApi!!.getTrendingMovies(ApiData.API_KEY, index)

            if (response.isSuccessful) {
                trendingMoviesLiveData.postValue(Resource.Success(response))
            } else {
                trendingMoviesLiveData.postValue(
                    Resource.Error(
                        response.message().toString(),
                        response
                    )
                )
            }
        } catch (e: Exception) {
            trendingMoviesLiveData.postValue(Resource.Error(e.localizedMessage?:"Something went wrong.",))
        }
    }

    //Top Rated Movies
    private val topRatedMoviesLiveData = MutableLiveData<Resource<Response<MovieListModel>>>()
    val topRatedMovies: LiveData<Resource<Response<MovieListModel>>>
        get() = topRatedMoviesLiveData

    suspend fun loadTopRatedMovies(index: Int) {
        try {
            val movieApi = RetrofitInstance.movieApi

            val response = movieApi!!.getTopRatedMovies(ApiData.API_KEY, index)

            if (response.isSuccessful) {
                topRatedMoviesLiveData.postValue(Resource.Success(response))
            } else {
                topRatedMoviesLiveData.postValue(
                    Resource.Error(
                        response.message().toString(),
                        response
                    )
                )
            }
        } catch (e: Exception) {
            topRatedMoviesLiveData.postValue(Resource.Error(e.localizedMessage?:"Something went wrong.",))
        }
    }

    //Latest Movies
    private val latestMoviesLiveData = MutableLiveData<Resource<Response<MovieListModel>>>()
    val latestMovies: LiveData<Resource<Response<MovieListModel>>>
        get() = latestMoviesLiveData

    suspend fun loadLatestMovies(index: Int) {
        try {
            val movieApi = RetrofitInstance.movieApi

            val response = movieApi!!.getLatestMovies(ApiData.API_KEY, index)

            if (response.isSuccessful) {
                latestMoviesLiveData.postValue(Resource.Success(response))
            } else {
                latestMoviesLiveData.postValue(
                    Resource.Error(
                        response.message().toString(),
                        response
                    )
                )
            }
        } catch (e: Exception) {
            latestMoviesLiveData.postValue(Resource.Error(e.localizedMessage?:"Something went wrong.",))
        }
    }

    //Upcoming Movies

    private val upcomingMoviesLiveData = MutableLiveData<Resource<Response<MovieListModel>>>()
    val upcomingMovies: LiveData<Resource<Response<MovieListModel>>>
        get() = upcomingMoviesLiveData

    suspend fun loadUpcomingMovies(index: Int) {

        try {
            val movieApi = RetrofitInstance.movieApi

            val response = movieApi!!.getUpcomingMovies(ApiData.API_KEY, index)

            if (response.isSuccessful) {
                upcomingMoviesLiveData.postValue(Resource.Success(response))
            } else {
                upcomingMoviesLiveData.postValue(
                    Resource.Error(
                        response.message().toString(),
                        response
                    )
                )
            }
        } catch (e: Exception) {
            upcomingMoviesLiveData.postValue(Resource.Error(e.localizedMessage?:"Something went wrong.",))
        }
    }


}
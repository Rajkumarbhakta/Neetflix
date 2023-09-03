package com.rkbapps.neetflix.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.movies.MovieListModel
import com.rkbapps.neetflix.models.movies.MovieResult
import com.rkbapps.neetflix.services.MovieApi
import com.rkbapps.neetflix.utils.Resource

class SeeMoreRepository(private val movieApi: MovieApi) {

    private val _movieList = MutableLiveData<Resource<MovieListModel>>()

    val movieList: LiveData<Resource<MovieListModel>>
        get() = _movieList

    private val _movieListLiveData = MutableLiveData<MutableList<MovieResult>>(mutableListOf())
    val movieListLiveData: LiveData<MutableList<MovieResult>>
        get() = _movieListLiveData



    suspend fun loadPopularMovies(page: Int) {
        val response = movieApi.getPopularMovies(page = page)
        if (response.isSuccessful && response.body() != null) {
            _movieList.postValue(Resource.Success(response.body()!!))
            val tempList = _movieListLiveData.value
            tempList?.addAll(response.body()!!.results)
            _movieListLiveData.postValue(tempList!!)
        } else {
            _movieList.postValue(Resource.Error(response.message()))
        }
    }

    suspend fun loadTrendingMovies(page: Int) {
        val response = movieApi.getTrendingMovies(page = page)
        if (response.isSuccessful && response.body() != null) {
            _movieList.postValue(Resource.Success(response.body()!!))
            val tempList = _movieListLiveData.value
            tempList?.addAll(response.body()!!.results)
            _movieListLiveData.postValue(tempList!!)
        } else {
            _movieList.postValue(Resource.Error(response.message()))
        }
    }

    suspend fun loadTopRatedMovies(page: Int) {
        val response = movieApi.getTopRatedMovies(page = page)
        if (response.isSuccessful && response.body() != null) {
            _movieList.postValue(Resource.Success(response.body()!!))
            val tempList = _movieListLiveData.value
            tempList?.addAll(response.body()!!.results)
            _movieListLiveData.postValue(tempList!!)
        } else {
            _movieList.postValue(Resource.Error(response.message()))
        }
    }

    suspend fun loadUpcomingMovies(page: Int) {
        val response = movieApi.getUpcomingMovies(page = page)
        if (response.isSuccessful && response.body() != null) {
            _movieList.postValue(Resource.Success(response.body()!!))
            val tempList = _movieListLiveData.value
            tempList?.addAll(response.body()!!.results)
            _movieListLiveData.postValue(tempList!!)
        } else {
            _movieList.postValue(Resource.Error(response.message()))
        }
    }


}
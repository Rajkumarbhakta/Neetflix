package com.rkbapps.neetflix.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.movies.MovieListModel
import com.rkbapps.neetflix.models.movies.MovieResult
import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel
import com.rkbapps.neetflix.models.tvseries.TvSeriesResult
import com.rkbapps.neetflix.services.MovieApi
import com.rkbapps.neetflix.services.TvSeriesApi
import com.rkbapps.neetflix.utils.Resource

class SeeMoreRepository(private val movieApi: MovieApi,private val tvSeriesApi: TvSeriesApi) {

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

    private val _tvSeriesData = MutableLiveData<Resource<TvSeriesListModel>>()

    val tvSeriesData: LiveData<Resource<TvSeriesListModel>>
        get() = _tvSeriesData

    private val _tvSeriesListLiveData = MutableLiveData<MutableList<TvSeriesResult>>(mutableListOf())
    val tvSeriesListLiveData: LiveData<MutableList<TvSeriesResult>>
        get() = _tvSeriesListLiveData

    suspend fun loadPopularTvSeries(page: Int) {
        val response = tvSeriesApi.getPopularSeries(page = page)
        if (response.isSuccessful && response.body() != null) {
            _tvSeriesData.postValue(Resource.Success(response.body()!!))
            val tempList = _tvSeriesListLiveData.value
            tempList?.addAll(response.body()!!.results)
            _tvSeriesListLiveData.postValue(tempList!!)
        } else {
            _tvSeriesData.postValue(Resource.Error(response.message()))
        }
    }

    suspend fun loadTrendingTvSeries(page: Int) {
        val response = tvSeriesApi.getTrendingSeries(page = page)
        if (response.isSuccessful && response.body() != null) {
            _tvSeriesData.postValue(Resource.Success(response.body()!!))
            val tempList = _tvSeriesListLiveData.value
            tempList?.addAll(response.body()!!.results)
            _tvSeriesListLiveData.postValue(tempList!!)
        } else {
            _tvSeriesData.postValue(Resource.Error(response.message()))
        }
    }

    suspend fun loadTopRatedTvSeries(page: Int) {
        val response = tvSeriesApi.getTopRatedSeries(page = page)
        if (response.isSuccessful && response.body() != null) {
            _tvSeriesData.postValue(Resource.Success(response.body()!!))
            val tempList = _tvSeriesListLiveData.value
            tempList?.addAll(response.body()!!.results)
            _tvSeriesListLiveData.postValue(tempList!!)
        } else {
            _tvSeriesData.postValue(Resource.Error(response.message()))
        }
    }

    suspend fun loadOnAirTvSeries(page: Int) {
        val response = tvSeriesApi.getAiringTodaySeries(page = page)
        if (response.isSuccessful && response.body() != null) {
            _tvSeriesData.postValue(Resource.Success(response.body()!!))
            val tempList = _tvSeriesListLiveData.value
            tempList?.addAll(response.body()!!.results)
            _tvSeriesListLiveData.postValue(tempList!!)
        } else {
            _tvSeriesData.postValue(Resource.Error(response.message()))
        }
    }



}
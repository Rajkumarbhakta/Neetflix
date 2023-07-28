package com.rkbapps.neetflix.repository.series

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.Resource
import retrofit2.Response

class SeriesFragmentRepository {

    //Trending series
    private val trendingSeriesLiveData = MutableLiveData<Resource<Response<TvSeriesListModel>>>()

    val trendingSeries: LiveData<Resource<Response<TvSeriesListModel>>>
        get() = trendingSeriesLiveData

    suspend fun loadTrendingSeries(page: Int) {
        val response = RetrofitInstance.tvSeriesApi!!.getTrendingSeries(ApiData.API_KEY, page)
        if (response.isSuccessful) {
            trendingSeriesLiveData.postValue(Resource.Success(response))
        } else {
            trendingSeriesLiveData.postValue(Resource.Error(response.message(), response))
        }
    }

    //Popular series
    private val popularSeriesLiveData = MutableLiveData<Resource<Response<TvSeriesListModel>>>()

    val popularSeries: LiveData<Resource<Response<TvSeriesListModel>>>
        get() = popularSeriesLiveData

    suspend fun loadPopularSeries(page: Int) {
        val response = RetrofitInstance.tvSeriesApi!!.getPopularSeries(ApiData.API_KEY, page)
        if (response.isSuccessful) {
            popularSeriesLiveData.postValue(Resource.Success(response))
        } else {
            popularSeriesLiveData.postValue(Resource.Error(response.message(), response))
        }
    }

    //Top rated series
    private val topRatedSeriesLiveData = MutableLiveData<Resource<Response<TvSeriesListModel>>>()

    val topRatedSeries: LiveData<Resource<Response<TvSeriesListModel>>>
        get() = topRatedSeriesLiveData

    suspend fun loadTopRatedSeries(page: Int) {
        val response = RetrofitInstance.tvSeriesApi!!.getTopRatedSeries(ApiData.API_KEY, page)
        if (response.isSuccessful) {
            topRatedSeriesLiveData.postValue(Resource.Success(response))
        } else {
            topRatedSeriesLiveData.postValue(Resource.Error(response.message(), response))
        }
    }

    //Trending series
    private val arrivingTodaySeriesLiveData =
        MutableLiveData<Resource<Response<TvSeriesListModel>>>()

    val arrivingTodaySeries: LiveData<Resource<Response<TvSeriesListModel>>>
        get() = arrivingTodaySeriesLiveData

    suspend fun loadArrivingTodaySeries(page: Int) {
        val response = RetrofitInstance.tvSeriesApi!!.getAiringTodaySeries(ApiData.API_KEY, page)
        if (response.isSuccessful) {
            arrivingTodaySeriesLiveData.postValue(Resource.Success(response))
        } else {
            arrivingTodaySeriesLiveData.postValue(Resource.Error(response.message(), response))
        }
    }


}
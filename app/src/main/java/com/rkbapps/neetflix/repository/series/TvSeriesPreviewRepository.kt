package com.rkbapps.neetflix.repository.series

import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.tvseries.TvSeriesModel
import com.rkbapps.neetflix.services.TvSeriesApi

class TvSeriesPreviewRepository(private val tvSeriesApi: TvSeriesApi) {

    private val _seriesDetails = MutableLiveData<TvSeriesModel?>()

    val seriesDetails: MutableLiveData<TvSeriesModel?>
        get() = _seriesDetails

    suspend fun getSeriesDetails(id: Int) {
        val response = tvSeriesApi.getSeriesDetails(id)
        if (response.isSuccessful) {
            _seriesDetails.postValue(response.body())
        } else {
            _seriesDetails.postValue(null)
        }
    }

}
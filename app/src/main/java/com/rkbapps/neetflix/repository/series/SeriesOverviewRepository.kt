package com.rkbapps.neetflix.repository.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.castandcrew.CreditsModel
import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel
import com.rkbapps.neetflix.models.tvseries.TvSeriesModel
import com.rkbapps.neetflix.services.TvSeriesApi

class SeriesOverviewRepository(private val tvSeriesApi: TvSeriesApi) {

    private val _seriesDetails = MutableLiveData<TvSeriesModel?>()
    val seriesDetails: LiveData<TvSeriesModel?>
        get() = _seriesDetails

    suspend fun getSeriesDetails(id: Int) {
        val response = tvSeriesApi.getSeriesDetails(id)
        if (response.isSuccessful) {
            _seriesDetails.postValue(response.body())
        } else {
            _seriesDetails.postValue(null)
        }
    }

    private val _seriesCredits = MutableLiveData<CreditsModel?>()
    val seriesCredits: LiveData<CreditsModel?>
        get() = _seriesCredits

    suspend fun getSeriesCredits(id: Int) {
        val response = tvSeriesApi.getSeriesCredits(id)
        if (response.isSuccessful) {
            _seriesCredits.postValue(response.body())
        } else {
            _seriesCredits.postValue(null)
        }
    }

    private val _seriesSimilar = MutableLiveData<TvSeriesListModel?>()
    val seriesSimilar: LiveData<TvSeriesListModel?>
        get() = _seriesSimilar

    suspend fun getSeriesSimilar(id: Int) {
        val response = tvSeriesApi.getSimilarTvSeries(id)
        if (response.isSuccessful) {
            _seriesSimilar.postValue(response.body())
        } else {
            _seriesSimilar.postValue(null)
        }
    }


}
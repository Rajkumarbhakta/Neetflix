package com.rkbapps.neetflix.viewmodels.series


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.models.tvseries.TvSeriesListModel
import com.rkbapps.neetflix.repository.series.SeriesFragmentRepository
import com.rkbapps.neetflix.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class SeriesFragmentViewModel(
    private val repository: SeriesFragmentRepository
) : ViewModel() {

    val arrivingTodaySeries: LiveData<Resource<Response<TvSeriesListModel>>>
        get() = repository.arrivingTodaySeries
    val topRatedSeries: LiveData<Resource<Response<TvSeriesListModel>>>
        get() = repository.topRatedSeries
    val popularSeries: LiveData<Resource<Response<TvSeriesListModel>>>
        get() = repository.popularSeries
    val trendingSeries: LiveData<Resource<Response<TvSeriesListModel>>>
        get() = repository.trendingSeries

    init {
        viewModelScope.launch {
            repository.loadPopularSeries(1)
            repository.loadTrendingSeries(1)
            repository.loadTopRatedSeries(1)
            repository.loadArrivingTodaySeries(1)
        }
    }

}
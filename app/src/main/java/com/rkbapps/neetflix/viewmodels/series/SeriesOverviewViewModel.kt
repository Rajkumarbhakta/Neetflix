package com.rkbapps.neetflix.viewmodels.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.repository.series.SeriesOverviewRepository
import kotlinx.coroutines.launch

class SeriesOverviewViewModel(
    private val repository: SeriesOverviewRepository,
    val id: Int,
) : ViewModel() {

    val seriesDetails
        get() = repository.seriesDetails
    val seriesCredits
        get() = repository.seriesCredits
    val seriesSimilar
        get() = repository.seriesSimilar

    init {
        viewModelScope.launch {
            repository.getSeriesDetails(id)
            repository.getSeriesCredits(id)
            repository.getSeriesSimilar(id)
        }
    }


}
package com.rkbapps.neetflix.viewmodels.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.repository.series.TvSeriesPreviewRepository
import kotlinx.coroutines.launch

class TvSeriesPreviewViewModel(
    private val repository: TvSeriesPreviewRepository,
    val id: Int,
) : ViewModel() {

    val seriesDetails
        get() = repository.seriesDetails

    init {
        viewModelScope.launch {
            repository.getSeriesDetails(id)
        }
    }

}
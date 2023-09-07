package com.rkbapps.neetflix.viewmodelfactories.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.series.TvSeriesPreviewRepository

class TvSeriesPreviewViewModelFactory(
    private val repository: TvSeriesPreviewRepository,
    private val id: Int,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return com.rkbapps.neetflix.viewmodels.series.TvSeriesPreviewViewModel(repository, id) as T
    }
}

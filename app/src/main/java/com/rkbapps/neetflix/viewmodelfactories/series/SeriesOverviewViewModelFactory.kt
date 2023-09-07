package com.rkbapps.neetflix.viewmodelfactories.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.series.SeriesOverviewRepository
import com.rkbapps.neetflix.viewmodels.series.SeriesOverviewViewModel


class SeriesOverviewViewModelFactory(
    private val repository: SeriesOverviewRepository,
    private val id: Int,
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SeriesOverviewViewModel(repository, id) as T
    }
}
package com.rkbapps.neetflix.viewmodelfactories.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.series.SeriesFragmentRepository
import com.rkbapps.neetflix.viewmodels.series.SeriesFragmentViewModel

class SeriesFragmentViewModelFactory(
    private val repository: SeriesFragmentRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SeriesFragmentViewModel(repository) as T
    }

}
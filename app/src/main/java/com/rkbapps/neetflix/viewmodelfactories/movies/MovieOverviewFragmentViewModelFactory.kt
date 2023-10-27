package com.rkbapps.neetflix.viewmodelfactories.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.movies.MovieOverviewFragmentRepository
import com.rkbapps.neetflix.viewmodels.movies.MovieOverviewFragmentViewModel

class MovieOverviewFragmentViewModelFactory(
    private val repository: MovieOverviewFragmentRepository,
    private val id: Int
) : ViewModelProvider.Factory {

    @Suppress("Unchecked cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieOverviewFragmentViewModel(repository, id) as T
    }


}

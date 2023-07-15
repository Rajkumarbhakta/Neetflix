package com.rkbapps.neetflix.viewmodelfactories.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.movies.MovieFragmentRepository
import com.rkbapps.neetflix.viewmodels.movies.MovieFragmentViewModel

class MovieFragmentViewModelFactory(private val repository: MovieFragmentRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieFragmentViewModel(repository) as T
    }
}
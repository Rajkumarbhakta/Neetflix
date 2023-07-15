package com.rkbapps.neetflix.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.MovieFragmentRepository
import com.rkbapps.neetflix.viewmodels.MovieFragmentViewModel

class MovieFragmentViewModelFactory(private val repository: MovieFragmentRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MovieFragmentViewModel(repository) as T
    }
}
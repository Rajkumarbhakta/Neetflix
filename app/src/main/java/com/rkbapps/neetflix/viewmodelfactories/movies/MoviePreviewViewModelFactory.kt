package com.rkbapps.neetflix.viewmodelfactories.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.movies.MoviePreviewRepository
import com.rkbapps.neetflix.viewmodels.movies.MoviePreviewViewModel

class MoviePreviewViewModelFactory(
    private val repository: MoviePreviewRepository,
    private val id: Int
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MoviePreviewViewModel(repository, id) as T
    }

}
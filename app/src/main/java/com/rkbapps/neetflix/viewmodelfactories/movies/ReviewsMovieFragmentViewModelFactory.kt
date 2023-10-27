package com.rkbapps.neetflix.viewmodelfactories.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.movies.ReviewsMovieFragmentRepository
import com.rkbapps.neetflix.viewmodels.movies.ReviewsMovieFragmentViewModel

class ReviewsMovieFragmentViewModelFactory(
    private val repository: ReviewsMovieFragmentRepository,
    private val id: Int
) : ViewModelProvider.Factory {
    @Suppress("Unchecked cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReviewsMovieFragmentViewModel(repository, id) as T
    }

}
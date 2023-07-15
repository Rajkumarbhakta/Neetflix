package com.rkbapps.neetflix.viewmodels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.models.Review.ReviewModel
import com.rkbapps.neetflix.repository.movies.ReviewsMovieFragmentRepository
import com.rkbapps.neetflix.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class ReviewsMovieFragmentViewModel(
    private val repository: ReviewsMovieFragmentRepository,
    private val id: Int
) : ViewModel() {

    val reviews: LiveData<Resource<Response<ReviewModel>>>
        get() = repository.reviews

    init {
        viewModelScope.launch {
            repository.loadMovieReviews(id)
        }
    }

}
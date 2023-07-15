package com.rkbapps.neetflix.viewmodels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.models.movies.MovieModel
import com.rkbapps.neetflix.repository.movies.MoviePreviewRepository
import com.rkbapps.neetflix.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MoviePreviewViewModel(private val repository: MoviePreviewRepository, private val id: Int) :
    ViewModel() {

    val movieData: LiveData<Resource<Response<MovieModel>>>
        get() = repository.movieData

    init {
        viewModelScope.launch {
            repository.loadMovieData(id)
        }
    }
}
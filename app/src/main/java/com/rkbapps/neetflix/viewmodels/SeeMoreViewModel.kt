package com.rkbapps.neetflix.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.models.movies.MovieListModel
import com.rkbapps.neetflix.models.movies.MovieResult
import com.rkbapps.neetflix.repository.SeeMoreRepository
import com.rkbapps.neetflix.utils.Resource
import kotlinx.coroutines.launch

class SeeMoreViewModel(private val repository: SeeMoreRepository) : ViewModel() {

    val movieList: LiveData<Resource<MovieListModel>>
        get() = repository.movieList

    val movieListLiveData: LiveData<MutableList<MovieResult>>
        get() = repository.movieListLiveData

    fun loadPopularMovies(page: Int) {
        viewModelScope.launch {
            repository.loadPopularMovies(page)
        }
    }

    fun loadTrendingMovies(page: Int) {
        viewModelScope.launch {
            repository.loadTrendingMovies(page)
        }
    }

    fun loadTopRatedMovies(page: Int) {
        viewModelScope.launch {
            repository.loadTopRatedMovies(page)
        }
    }

    fun loadUpcomingMovies(page: Int) {
        viewModelScope.launch {
            repository.loadUpcomingMovies(page)
        }
    }


}
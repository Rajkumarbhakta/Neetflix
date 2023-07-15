package com.rkbapps.neetflix.viewmodels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.models.movies.MovieListModel
import com.rkbapps.neetflix.repository.movies.MovieFragmentRepository
import com.rkbapps.neetflix.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieFragmentViewModel(private val repository: MovieFragmentRepository) : ViewModel() {


    val popularMovies: LiveData<Resource<Response<MovieListModel>>>
        get() = repository.popularMovies
    val trendingMovies: LiveData<Resource<Response<MovieListModel>>>
        get() = repository.trendingMovies
    val topRatedMovies: LiveData<Resource<Response<MovieListModel>>>
        get() = repository.topRatedMovies
    val latestMovies: LiveData<Resource<Response<MovieListModel>>>
        get() = repository.latestMovies
    val upcomingMovies: LiveData<Resource<Response<MovieListModel>>>
        get() = repository.upcomingMovies


    init {
        viewModelScope.launch {
            repository.loadPopularMovies(1)
            repository.loadTrendingMovies(1)
            repository.loadTopRatedMovies(1)
            //repository.loadLatestMovies(1)
            repository.loadUpcomingMovies(1)
        }
    }


}
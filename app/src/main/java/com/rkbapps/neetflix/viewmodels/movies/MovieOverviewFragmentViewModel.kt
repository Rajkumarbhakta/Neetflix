package com.rkbapps.neetflix.viewmodels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.models.castandcrew.CreditsModel
import com.rkbapps.neetflix.models.movies.MovieListModel
import com.rkbapps.neetflix.models.movies.MovieModel
import com.rkbapps.neetflix.repository.movies.MovieOverviewFragmentRepository
import com.rkbapps.neetflix.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieOverviewFragmentViewModel(
    private val repository: MovieOverviewFragmentRepository,
    private val id: Int
) : ViewModel() {

    val movieDetails: LiveData<Resource<Response<MovieModel>>>
        get() = repository.movieDetails
    val creditsData: LiveData<Resource<Response<CreditsModel>>>
        get() = repository.creditsData
    val similarMoviesData: LiveData<Resource<Response<MovieListModel>>>
        get() = repository.similarMoviesData

    init {
        viewModelScope.launch {
            repository.loadMovieDetails(id)
            repository.loadCredits(id)
            repository.loadSimilarMovies(id)
        }
    }


}
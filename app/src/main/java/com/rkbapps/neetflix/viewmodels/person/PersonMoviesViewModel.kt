package com.rkbapps.neetflix.viewmodels.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.models.person.movie.WorkForMovies
import com.rkbapps.neetflix.repository.person.PersonMoviesRepository
import com.rkbapps.neetflix.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PersonMoviesViewModel(
    private val repository: PersonMoviesRepository,
    private val id: Int
) : ViewModel() {

    val personMovies: LiveData<Resource<Response<WorkForMovies>>>
        get() = repository.personMovies

    init {
        viewModelScope.launch {
            repository.loadPersonMovies(id)
        }
    }


}
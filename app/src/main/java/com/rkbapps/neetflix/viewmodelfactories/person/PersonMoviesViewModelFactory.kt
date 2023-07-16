package com.rkbapps.neetflix.viewmodelfactories.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.person.PersonMoviesRepository
import com.rkbapps.neetflix.viewmodels.person.PersonMoviesViewModel

class PersonMoviesViewModelFactory(
    private val repository: PersonMoviesRepository,
    private val id: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonMoviesViewModel(repository, id) as T
    }

}
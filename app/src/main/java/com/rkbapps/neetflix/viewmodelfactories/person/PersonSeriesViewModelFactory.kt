package com.rkbapps.neetflix.viewmodelfactories.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.person.PersonSeriesRepository
import com.rkbapps.neetflix.viewmodels.person.PersonSeriesViewModel

class PersonSeriesViewModelFactory(
    private val repository: PersonSeriesRepository,
    private val id:Int
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonSeriesViewModel(repository, id) as T
    }

}
package com.rkbapps.neetflix.viewmodelfactories.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.person.PersonImageRepository
import com.rkbapps.neetflix.viewmodels.person.PersonImageViewModel

class PersonImageViewModelFactory(
    private val repository:PersonImageRepository,
    private val id:Int
) :ViewModelProvider.Factory{
    @Suppress("Unchecked cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonImageViewModel(repository, id) as T
    }

}
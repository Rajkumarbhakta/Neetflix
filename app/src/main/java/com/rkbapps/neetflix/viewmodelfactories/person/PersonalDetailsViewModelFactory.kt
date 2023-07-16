package com.rkbapps.neetflix.viewmodelfactories.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.person.PersonalDetailsRepository
import com.rkbapps.neetflix.viewmodels.person.PersonalDetailViewModel

class PersonalDetailsViewModelFactory(
    private val repository: PersonalDetailsRepository,
    private val personId:Int
) :ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonalDetailViewModel(repository, personId) as T
    }

}
package com.rkbapps.neetflix.viewmodels.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.models.person.PersonDetails
import com.rkbapps.neetflix.repository.person.PersonalDetailsRepository
import com.rkbapps.neetflix.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PersonalDetailViewModel(
    private val repository: PersonalDetailsRepository,
    private val personId: Int
) : ViewModel() {

    val personalDetails: LiveData<Resource<Response<PersonDetails>>>
        get() = repository.personalDetails

    init {
        viewModelScope.launch {
            repository.loadPersonalDetails(personId)
        }
    }

}
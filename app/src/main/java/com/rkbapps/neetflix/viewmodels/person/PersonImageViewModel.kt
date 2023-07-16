package com.rkbapps.neetflix.viewmodels.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.models.person.images.PersonImageModel
import com.rkbapps.neetflix.repository.person.PersonImageRepository
import com.rkbapps.neetflix.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PersonImageViewModel(
    private val repository: PersonImageRepository,
    private val id: Int
) : ViewModel() {

    val images: LiveData<Resource<Response<PersonImageModel>>>
        get() = repository.images

    init {
        viewModelScope.launch {
            repository.loadImages(id)
        }
    }

}
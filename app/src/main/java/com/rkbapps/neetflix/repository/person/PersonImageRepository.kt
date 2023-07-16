package com.rkbapps.neetflix.repository.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.person.images.PersonImageModel
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.Resource
import retrofit2.Response

class PersonImageRepository {

    private val imagesLiveData = MutableLiveData<Resource<Response<PersonImageModel>>>()

    val images: LiveData<Resource<Response<PersonImageModel>>>
        get() = imagesLiveData

    suspend fun loadImages(personId: Int) {
        val response = RetrofitInstance.getPersonApi().getPersonImages(personId, ApiData.API_KEY)
        if (response.isSuccessful) {
            imagesLiveData.postValue(Resource.Success(response))
        } else {
            imagesLiveData.postValue(Resource.Error(response.message(), response))
        }
    }

}
package com.rkbapps.neetflix.repository.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.person.PersonDetails
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.Resource
import retrofit2.Response

class PersonalDetailsRepository {

    private val personalDetailsLiveData = MutableLiveData<Resource<Response<PersonDetails>>>()

    val personalDetails: LiveData<Resource<Response<PersonDetails>>>
        get() = personalDetailsLiveData

    suspend fun loadPersonalDetails(personId: Int) {
        val response = RetrofitInstance.personApi!!.getPersonDetails(personId, ApiData.API_KEY)

        if (response.isSuccessful) {
            personalDetailsLiveData.postValue(Resource.Success(response))
        } else {
            personalDetailsLiveData.postValue(Resource.Error(response.message(), response))
        }
    }


}
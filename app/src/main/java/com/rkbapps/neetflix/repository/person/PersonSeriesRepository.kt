package com.rkbapps.neetflix.repository.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.person.tvseries.WorkForSeries
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.Resource
import retrofit2.Response

class PersonSeriesRepository {

    private val personSeriesLiveData = MutableLiveData<Resource<Response<WorkForSeries>>>()
    val personSeries: LiveData<Resource<Response<WorkForSeries>>>
        get() = personSeriesLiveData

    suspend fun loadPersonSeries(id:Int){
        val response = RetrofitInstance.personApi!!.getPersonSeriesCredits(id,ApiData.API_KEY)

        if(response.isSuccessful){
            personSeriesLiveData.postValue(Resource.Success(response))
        }else{
            personSeriesLiveData.postValue(Resource.Error(response.message(),response))
        }
    }


}
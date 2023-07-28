package com.rkbapps.neetflix.repository.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.person.movie.WorkForMovies
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.Resource
import retrofit2.Response

class PersonMoviesRepository {

    private val personMoviesLiveData = MutableLiveData<Resource<Response<WorkForMovies>>>()

    val personMovies: LiveData<Resource<Response<WorkForMovies>>>
        get() = personMoviesLiveData

    suspend fun loadPersonMovies(personId: Int) {
        val response =
            RetrofitInstance.personApi!!.getPersonMovieCredits(personId, ApiData.API_KEY)

        if(response.isSuccessful){
            personMoviesLiveData.postValue(Resource.Success(response))
        }else{
            personMoviesLiveData.postValue(Resource.Error(response.message(),response))
        }
    }

}
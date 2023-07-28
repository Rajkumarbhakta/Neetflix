package com.rkbapps.neetflix.repository.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.Review.ReviewModel
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.Resource
import retrofit2.Response

class ReviewsMovieFragmentRepository {

    private val reviewsLiveDate = MutableLiveData<Resource<Response<ReviewModel>>>()

    val reviews: LiveData<Resource<Response<ReviewModel>>>
        get() = reviewsLiveDate

    suspend fun loadMovieReviews(id: Int) {
        val response = RetrofitInstance.movieApi!!.getMovieReviews(id, ApiData.API_KEY, "en-US")

        if (response.isSuccessful) {
            reviewsLiveDate.postValue(Resource.Success(response))
        } else {
            reviewsLiveDate.postValue(Resource.Error(response.message(), response))
        }
    }
}
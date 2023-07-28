package com.rkbapps.neetflix.repository.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.images.ImagesModel
import com.rkbapps.neetflix.models.videos.VideoModel
import com.rkbapps.neetflix.services.ApiData
import com.rkbapps.neetflix.services.RetrofitInstance
import com.rkbapps.neetflix.utils.Resource
import retrofit2.Response

class VideoAndImageRepository {

    private val imagesLiveData = MutableLiveData<Resource<Response<ImagesModel>>>()
    val images: LiveData<Resource<Response<ImagesModel>>>
        get() = imagesLiveData

    suspend fun loadImages(id: Int) {
        val response = RetrofitInstance.movieApi!!.getMovieImages(id, ApiData.API_KEY)
        if (response.isSuccessful) {
            imagesLiveData.postValue(Resource.Success(response))
        } else {
            imagesLiveData.postValue(Resource.Error(response.message(), response))
        }
    }


    private val videosLiveData = MutableLiveData<Resource<Response<VideoModel>>>()
    val videos: LiveData<Resource<Response<VideoModel>>>
        get() = videosLiveData

    suspend fun loadVideos(id: Int) {
        val response = RetrofitInstance.movieApi!!.getMovieVideos(id, ApiData.API_KEY)
        if (response.isSuccessful) {
            videosLiveData.postValue(Resource.Success(response))
        } else {
            videosLiveData.postValue(Resource.Error(response.message(), response))
        }
    }


}
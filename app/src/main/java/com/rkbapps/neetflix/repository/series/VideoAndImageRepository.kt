package com.rkbapps.neetflix.repository.series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rkbapps.neetflix.models.images.ImagesModel
import com.rkbapps.neetflix.models.videos.VideoModel
import com.rkbapps.neetflix.services.TvSeriesApi

class VideoAndImageRepository(private val tvSeriesApi: TvSeriesApi) {

    private val _images = MutableLiveData<ImagesModel?>()
    val images: LiveData<ImagesModel?>
        get() = _images

    suspend fun getImages(id: Int) {
        val response = tvSeriesApi.getSeriesImages(id)
        if (response.isSuccessful) {
            _images.postValue(response.body())
        } else {
            _images.postValue(null)
        }
    }

    private val _videos = MutableLiveData<VideoModel?>()
    val videos: LiveData<VideoModel?>
        get() = _videos

    suspend fun getVideos(id: Int) {
        val response = tvSeriesApi.getSeriesVideos(id)
        if (response.isSuccessful) {
            _videos.postValue(response.body())
        } else {
            _videos.postValue(null)
        }

    }

}
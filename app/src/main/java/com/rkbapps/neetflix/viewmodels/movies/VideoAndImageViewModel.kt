package com.rkbapps.neetflix.viewmodels.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.models.images.ImagesModel
import com.rkbapps.neetflix.models.videos.VideoModel
import com.rkbapps.neetflix.repository.movies.VideoAndImageRepository
import com.rkbapps.neetflix.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class VideoAndImageViewModel(
    private val repository: VideoAndImageRepository,
    private val id: Int
) : ViewModel() {

    val images: LiveData<Resource<Response<ImagesModel>>>
        get() = repository.images
    val videos: LiveData<Resource<Response<VideoModel>>>
        get() = repository.videos

    init {
        viewModelScope.launch {
            repository.loadImages(id)
            repository.loadVideos(id)
        }
    }


}
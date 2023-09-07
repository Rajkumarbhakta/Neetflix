package com.rkbapps.neetflix.viewmodels.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.repository.series.VideoAndImageRepository
import kotlinx.coroutines.launch

class VideoImageViewModel(private val repository: VideoAndImageRepository, private val id: Int) :
    ViewModel() {

    val images
        get() = repository.images

    val videos
        get() = repository.videos

    init {
        viewModelScope.launch {
            repository.getImages(id)
            repository.getVideos(id)
        }
    }

}
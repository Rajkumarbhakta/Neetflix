package com.rkbapps.neetflix.viewmodelfactories.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.series.VideoAndImageRepository
import com.rkbapps.neetflix.viewmodels.series.VideoImageViewModel

class VideoAndImageViewModelFactory(private val repository: VideoAndImageRepository, private val id: Int):
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return VideoImageViewModel(repository, id) as T
    }

}
package com.rkbapps.neetflix.viewmodelfactories.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.movies.VideoAndImageRepository
import com.rkbapps.neetflix.viewmodels.movies.VideoAndImageViewModel

class VideoAndImagesViewModelFactory(
    private val repository: VideoAndImageRepository,
    private val id: Int
) : ViewModelProvider.Factory {
    @Suppress("Unchecked cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VideoAndImageViewModel(repository, id) as T
    }

}
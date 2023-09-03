package com.rkbapps.neetflix.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rkbapps.neetflix.repository.SeeMoreRepository
import com.rkbapps.neetflix.viewmodels.SeeMoreViewModel

class SeeMoreViewModelFactory(private val repository: SeeMoreRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SeeMoreViewModel(repository) as T
    }
}
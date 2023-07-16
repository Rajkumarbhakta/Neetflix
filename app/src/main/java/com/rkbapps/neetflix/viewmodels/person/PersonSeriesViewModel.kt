package com.rkbapps.neetflix.viewmodels.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkbapps.neetflix.models.person.tvseries.WorkForSeries
import com.rkbapps.neetflix.repository.person.PersonSeriesRepository
import com.rkbapps.neetflix.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PersonSeriesViewModel(
    private val repository: PersonSeriesRepository,
    private val id: Int
) : ViewModel() {
    val personSeries: LiveData<Resource<Response<WorkForSeries>>>
        get() = repository.personSeries

    init {
        viewModelScope.launch {
            repository.loadPersonSeries(id)
        }
    }
}
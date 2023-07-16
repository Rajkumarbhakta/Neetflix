package com.rkbapps.neetflix.models.person.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WorkForMovies(
    @SerializedName("cast")
    @Expose
    val cast: List<AsACastModel>,
    @SerializedName("crew")
    @Expose
    val crew: List<AsACrewModel>,
    @SerializedName("id")
    @Expose
    val id: Int
)
package com.rkbapps.neetflix.models.tvseries

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvSeriesResult(
    @SerializedName("first_air_date")
    @Expose
    val firstAirDate: String,

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String?,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double,

    )
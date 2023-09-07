package com.rkbapps.neetflix.models.person.movie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AsACrewModel(
    @SerializedName("adult")
    @Expose
    val adult: Boolean,

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("original_title")
    @Expose
    val originalTitle: String,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String,

    @SerializedName("release_date")
    @Expose
    val releaseDate: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,

    @SerializedName("department")
    @Expose
    val department: String,

    @SerializedName("job")
    @Expose
    val job: String,

    )
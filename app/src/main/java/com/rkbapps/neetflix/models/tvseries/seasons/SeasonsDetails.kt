package com.rkbapps.neetflix.models.tvseries.seasons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SeasonsDetails(
    @SerializedName("air_date")
    @Expose
    val airDate: String,

    @SerializedName("episodes")
    @Expose
    val episodes: List<EpisodeDetails>,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("overview")
    @Expose
    val overview: String,

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,

    @SerializedName("season_number")
    @Expose
    val seasonNumber: Int,
)
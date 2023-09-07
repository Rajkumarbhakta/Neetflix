package com.rkbapps.neetflix.models.tvseries

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Episode(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("overview")
    @Expose
    var overview: String,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int,

    @SerializedName("air_date")
    @Expose
    var airDate: String,

    @SerializedName("episode_number")
    @Expose
    var episodeNumber: Int,

    @SerializedName("production_code")
    @Expose
    var productionCode: String,

    @SerializedName("runtime")
    @Expose
    var runtime: Int,

    @SerializedName("season_number")
    @Expose
    var seasonNumber: Int,

    @SerializedName("show_id")
    @Expose
    var showId: Int,

    @SerializedName("still_path")
    @Expose
    var stillPath: String,
)
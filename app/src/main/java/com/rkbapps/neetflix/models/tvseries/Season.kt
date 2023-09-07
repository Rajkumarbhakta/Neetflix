package com.rkbapps.neetflix.models.tvseries

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Season(

    @SerializedName("air_date")
    @Expose
    var airDate: String,

    @SerializedName("episode_count")
    @Expose
    var episodeCount: Int,

    @SerializedName("id")
    @Expose
    var id: Int,

    @JvmField
    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("overview")
    @Expose
    var overview: String,


    @SerializedName("poster_path")
    @Expose
    var posterPath: String?,

    @SerializedName("season_number")
    @Expose
    var seasonNumber: Int,
) {

    val totalEpisodeCount: String
        get() = "$episodeCount E"
}

/*
@SerializedName("air_date")
    @Expose
    private String airDate;
    @SerializedName("episode_count")
    @Expose
    private Integer episodeCount;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("season_number")
    @Expose
    private Integer seasonNumber;
 */
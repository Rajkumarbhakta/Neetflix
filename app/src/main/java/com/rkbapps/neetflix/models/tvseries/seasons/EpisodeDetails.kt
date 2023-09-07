package com.rkbapps.neetflix.models.tvseries.seasons

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rkbapps.neetflix.models.castandcrew.Cast
import java.io.Serializable

data class EpisodeDetails(
    @SerializedName("air_date")
    @Expose
    val airDate: String,

    @SerializedName("episode_number")
    @Expose
    val episodeNumber: Int,

    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("overview")
    @Expose
    val overview: String,

    @SerializedName("runtime")
    @Expose
    val runtime: Int,

    @SerializedName("season_number")
    @Expose
    val seasonNumber: Int,

    @SerializedName("show_id")
    @Expose
    val showId: Int,

    @SerializedName("still_path")
    @Expose
    val stillPath: String?,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,

    @SerializedName("vote_count")
    @Expose
    val voteCount: Int,

    @SerializedName("guest_stars")
    @Expose
    val guestStars: List<Cast>,

    ) : Serializable
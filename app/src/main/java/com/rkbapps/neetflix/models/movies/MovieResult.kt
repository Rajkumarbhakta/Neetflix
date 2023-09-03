package com.rkbapps.neetflix.models.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("adult")
    @Expose
    var adult: Boolean,

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String,

    @SerializedName("release_date")
    @Expose
    var releaseDate: String,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double
)


/*
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
 */
package com.rkbapps.neetflix.models.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieListModel(
    @SerializedName("page")
    @Expose
    var page: Int,

    @SerializedName("results")
    @Expose
    var results: List<MovieResult>,

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int,

    @SerializedName("total_results")
    @Expose
    var totalResults: Int,

    ) {
    constructor() : this(0, ArrayList(), 0, 0)
}


/*
{
//    @SerializedName("page")
//    @Expose
//    var page: Int? = null
//
//    @SerializedName("results")
//    @Expose
//    var results: List<MovieResult>? = null
//
//    @SerializedName("total_pages")
//    @Expose
//    var totalPages: Int? = null
//
//    @SerializedName("total_results")
//    @Expose
//    var totalResults: Int? = null
}
 */

/*
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<MovieResult> movieResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
 */
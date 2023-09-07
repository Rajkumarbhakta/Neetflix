package com.rkbapps.neetflix.models.tvseries

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvSeriesListModel(
    @SerializedName("page")
    @Expose
    var page: Int,

    @SerializedName("results")
    @Expose
    var results: List<TvSeriesResult>,

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int,

    @SerializedName("total_results")
    @Expose
    var totalResults: Int,
){
    constructor(): this(0, emptyList(), 0, 0)
}
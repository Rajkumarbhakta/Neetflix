package com.rkbapps.neetflix.models.tvseries

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Network(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("logo_path")
    @Expose
    var logoPath: String?,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("origin_country")
    @Expose
    var originCountry: String,
)
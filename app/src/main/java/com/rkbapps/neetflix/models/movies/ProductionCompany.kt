package com.rkbapps.neetflix.models.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductionCompany(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("logo_path")
    @Expose
    val logoPath: String,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("origin_country")
    @Expose
    val originCountry: String,

    )
//{
//    @SerializedName("id")
//    @Expose
//    var id: Int? = null
//
//    @SerializedName("logo_path")
//    @Expose
//    var logoPath: String? = null
//
//    @SerializedName("name")
//    @Expose
//    var name: String? = null
//
//    @SerializedName("origin_country")
//    @Expose
//    var originCountry: String? = null
//}
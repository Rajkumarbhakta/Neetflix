package com.rkbapps.neetflix.models.tvseries

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CreatedBy(

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("credit_id")
    @Expose
    var creditId: String,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("gender")
    @Expose
    var gender: Int,

    @SerializedName("profile_path")
    @Expose
    var profilePath: String?,

    )
package com.rkbapps.neetflix.models.person.images

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PersonImageModel(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("profiles")
    @Expose
    val profiles: List<Profile>,
)
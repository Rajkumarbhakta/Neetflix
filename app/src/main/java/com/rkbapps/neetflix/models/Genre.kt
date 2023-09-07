package com.rkbapps.neetflix.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
)

/*

   @SerializedName("id")
    @Expose
    private Integer id;
@SerializedName("name")
@Expose
private val name:String;
 */
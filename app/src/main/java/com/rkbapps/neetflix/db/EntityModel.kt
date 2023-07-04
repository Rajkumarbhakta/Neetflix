package com.rkbapps.neetflix.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "content")
data class EntityModel(
    @PrimaryKey(autoGenerate = true) val contentId: Int,
    val adult: Boolean,
    val id: Int,
    val releaseDate: String,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
    val type: Int
)
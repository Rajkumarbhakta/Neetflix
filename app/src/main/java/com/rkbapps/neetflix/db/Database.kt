package com.rkbapps.neetflix.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EntityModel::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val contentDao: ContentDao
}
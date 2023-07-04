package com.rkbapps.neetflix.db

import android.content.Context
import androidx.room.Room.databaseBuilder
import kotlinx.coroutines.CoroutineScope

object DatabaseReference {
    @Volatile
    private var database: Database? = null

    fun getDatabase(context: Context): Database {
        if (database == null) {
            synchronized(this) {
                database =
                    databaseBuilder(context.applicationContext, Database::class.java, "bookmarks")
                        .allowMainThreadQueries()
                        .build()
            }
        }
        return database!!
    }
}
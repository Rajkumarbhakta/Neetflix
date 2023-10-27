package com.rkbapps.neetflix.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContentDao {
    //For inserting new data in database;
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToBookmark(entityModel: EntityModel?): Long

    //for deleting a data from database;
    @Delete
    suspend fun deleteBookmark(entityModel: EntityModel?)

    //get all bookmarked data from database
    @Query("select * from content")
    fun getAllMyBookmarks(): LiveData<List<EntityModel>>

    @Query("select exists(select * from content where id=:enterId)")
    fun isBookmarked(enterId: Int): Boolean

    @Query("delete from content where id=:enterId")
    suspend fun removeBookmark(enterId: Int)
}
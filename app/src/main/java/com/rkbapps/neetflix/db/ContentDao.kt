package com.rkbapps.neetflix.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContentDao {

    //For inserting new data in database;
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addToBookmark(EntityModel entityModel);

    //for deleting a data from database;
    @Delete
    void deleteBookmark(EntityModel entityModel);

    //get all bookmarked data from database
    @Query("select * from content")
    List<EntityModel> getMyBookmarks();

    @Query("select id from content where id=:enterId")
    boolean isBookmarked(int enterId);

    @Query("delete from content where id=:enterId")
    void removeBookmark(int enterId);

}

package com.rkbapps.neetflix.db;

import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {EntityModel.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract ContentDao getContentDao();


}

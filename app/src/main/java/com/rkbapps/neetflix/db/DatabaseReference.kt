package com.rkbapps.neetflix.db;

import android.content.Context;

import androidx.room.Room;

public class DatabaseReference {

    private static Database database = null;

    public static Database getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, Database.class, "my_bookmark")
                    .allowMainThreadQueries()
                    .build();
        }
        return database;
    }
}

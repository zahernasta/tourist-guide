package com.example.touristguide.database;


import android.content.Context;
import android.provider.ContactsContract;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.touristguide.database.dao.UserDao;
import com.example.touristguide.database.models.Users;

@Database(entities = {Users.class},
            exportSchema = false,
            version = 1)
public abstract class DatabaseManager
        extends RoomDatabase {
    private static final String DB_NAME = "db_android";
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context) {
        if(databaseManager == null) {
            synchronized (DatabaseManager.class) {
                if(databaseManager == null) {
                    databaseManager = Room.databaseBuilder(context,
                            DatabaseManager.class, DB_NAME).fallbackToDestructiveMigration().build();
                    return databaseManager;
                }
            }
        }
        return databaseManager;
    }

    public abstract UserDao getUserDao();
}

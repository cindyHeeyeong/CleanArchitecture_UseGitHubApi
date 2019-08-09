package com.example.cleanarchitecture_toyproject.data.cache.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 8)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}

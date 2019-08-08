package com.example.cleanarchitecture_toyproject.data.cache.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;

//@Database(entities = {UserModel.class}, version = 3)
@Database(entities = {UserEntity.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();

}

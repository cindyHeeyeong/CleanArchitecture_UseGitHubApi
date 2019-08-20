package com.example.cleanarchitecture_toyproject.data.cache.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity

@Database(entities = [UserEntity::class], version = 10)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    /*companion object {
        fun makeDataBase(context: Context): AppDatabase {
            Log.d("databaseprovider", context.toString())
            return Room.databaseBuilder(context, AppDatabase::class.java, "UserModel_database")
                .fallbackToDestructiveMigration()
                .build()
        }
    }*/

}

package com.example.cleanarchitecture_toyproject.provider

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.cleanarchitecture_toyproject.data.cache.database.AppDatabase
import com.example.cleanarchitecture_toyproject.viewmodel.AndroidApplication


//Room builder
class DatabaseProvider(private val context: Context) {
    val dataBase: AppDatabase

    init {
        Log.d("DatabaseProvider_2", context.toString())
        dataBase = makeDataBase()
    }

    fun makeDataBase(): AppDatabase {
        Log.d("databaseprovider", context.toString())
        return Room.databaseBuilder(context, AppDatabase::class.java, "UserModel_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        private var instance: DatabaseProvider? = null

        fun getInstance(): DatabaseProvider? {
            if (instance == null) {
                synchronized(DatabaseProvider::class.java) {
                    if (instance == null) {
                        instance = DatabaseProvider(AndroidApplication.Companion.INSTANCE)
                    }
                }
            }
            return instance
        }
    }
}

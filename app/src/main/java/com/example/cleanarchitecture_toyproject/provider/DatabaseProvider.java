package com.example.cleanarchitecture_toyproject.provider;

import android.content.Context;
import android.util.Log;
import androidx.room.Room;
import com.example.cleanarchitecture_toyproject.data.cache.database.AppDatabase;
import com.example.cleanarchitecture_toyproject.viewmodel.AndroidApplication;

public class DatabaseProvider {
    private static DatabaseProvider instance;
    private Context context;
    private AppDatabase appDatabase;

    public DatabaseProvider(Context context){
        this.context = context;
        Log.d("DatabaseProvider_2", String.valueOf(context));
        appDatabase = makeDataBase();
    }

    public static DatabaseProvider getInstance() {
        if (instance == null) {
            synchronized (DatabaseProvider.class) {
                if (instance == null) {
                    instance = new DatabaseProvider(AndroidApplication.getInstance());
                }
            }
        }
        return instance;
    }

    public AppDatabase getDataBase(){
        return appDatabase;
    }

    public AppDatabase makeDataBase(){
        Log.d("databaseprovider", String.valueOf(context));
        return Room.databaseBuilder(context, AppDatabase.class,"UserModel_database")
                .fallbackToDestructiveMigration()
                .build();
    }
}

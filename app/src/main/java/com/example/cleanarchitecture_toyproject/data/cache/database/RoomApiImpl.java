package com.example.cleanarchitecture_toyproject.data.cache.database;

import android.app.Application;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;
import com.example.cleanarchitecture_toyproject.domain.executor.DiskIoThreadExecutor;
import com.example.cleanarchitecture_toyproject.provider.DatabaseProvider;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;

//usercache

//이거 안쓰는거 지우기
public class RoomApiImpl implements RoomApi {


    public static RoomApiImpl roomApiImpl;

    //executor
    private static DiskIoThreadExecutor diskIoThreadExecutor;

    //room
    private static UserDao userDao;
    private static Context context;
    Application application;

    private static AppDatabase appDatabase;

    private static DatabaseProvider databaseProvider;

    public RoomApiImpl(DiskIoThreadExecutor diskIoThreadExecutor, UserDao userDao, DatabaseProvider databaseProvider) {
        this.diskIoThreadExecutor = diskIoThreadExecutor;
        this.userDao = userDao;
        //this.appDatabase = appDatabase;


        Log.d("context", String.valueOf(context));

        //databaseProvider.getDataBase();


        Log.d("databaseProvider",String.valueOf(databaseProvider));
        //userDao = appDatabase.userDao();

        /*AppDatabase appDatabase = AppDatabase.getInstance(context);
        userDao = appDatabase.userDao();*/
    }

    /*public static RoomApi getInstance(*//*AppDatabase appDatabase*//*) {
        if (roomApiImpl == null) {
            synchronized (RoomApiImpl.class) {
                if (roomApiImpl == null) {
                    roomApiImpl = new RoomApiImpl(new DiskIoThreadExecutor(), userDao);
                }
            }
        }
        return roomApiImpl;
    }*/

    @Override
    public void setUsers(UserEntity userModel) {
        Log.d("RoomApiImpl" , String.valueOf(userDao));
        diskIoThreadExecutor.execute(() -> userDao.insert(userModel));
    }
}

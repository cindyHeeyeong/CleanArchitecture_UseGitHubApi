package com.example.cleanarchitecture_toyproject.data.user;

import android.util.Log;
import com.example.cleanarchitecture_toyproject.data.cache.database.AppDatabase;
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;
import com.example.cleanarchitecture_toyproject.provider.DatabaseProvider;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
import io.reactivex.Observable;

import java.util.List;

public class UserCacheSourceImpl implements UserCacheSource {
    private static UserCacheSourceImpl userCacheSource;
    private final AppDatabase database;

    private UserCacheSourceImpl(AppDatabase database) {
        this.database = database;
    }

    public static UserCacheSource getInstance() {
        if (userCacheSource == null) {
            synchronized (UserCacheSourceImpl.class) {
                if (userCacheSource == null) {
                    userCacheSource = new UserCacheSourceImpl(DatabaseProvider.getInstance().getDataBase());
                }
            }
        }
        return userCacheSource;
    }

    @Override
    public UserEntity getUserList(int id) {
        return database.userDao().loadIDUser(id);
    }

    @Override
    public Observable<List<UserEntity>> selectUsers() {
        Log.d("selectUsers","selectUsers");
        return database.userDao().loadAllUser();
    }

    @Override
    public void deleteUsers(UserEntity userEntity) {
        database.userDao().deleteUser(userEntity);
    }

    //remote에 있는 getuser불러오기
    @Override
    public void setUsers(UserEntity userEntity) {
        database.userDao().insert(userEntity);
    }
}
package com.example.cleanarchitecture_toyproject.data.cache.database;

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;
import com.example.cleanarchitecture_toyproject.domain.User;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
import io.reactivex.Observable;

import java.util.List;

public interface RoomApi {
    //Observable<List<UserEntity>> setUsers();
    public void setUsers(UserEntity userModel);
}

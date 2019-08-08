package com.example.cleanarchitecture_toyproject.data.user;

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
import io.reactivex.Observable;

import java.util.List;

public interface UserCacheSource {

    //Insert Favorite User
    //Observable<UserEntity> setUsers(UserModel user);
    public void setUsers(UserEntity userEntity);

    //Observable<UserModel> getUserList();
    UserEntity getUserList(int id);

    //select Favorite User
    Observable<List<UserEntity>> selectUsers();

    //delete Favorite User
    public void deleteUsers(UserEntity userEntity);
}

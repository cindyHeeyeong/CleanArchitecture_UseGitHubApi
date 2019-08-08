package com.example.cleanarchitecture_toyproject.data.user;

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
import io.reactivex.Observable;

import java.util.List;

public interface UserDataSource {
    //사용자 이름 검색 후 사용자 list 가져오기
    Observable<List<UserEntity>> getUsers(String userName);

    //db의 유저 정보 가져오기
    Observable<List<UserEntity>> selectUsers();

    //Insert Favorite User
    void setUsers(UserEntity userEntity);

    //Delete Favorite User
    void deleteUsers(UserEntity userEntity);

}

package com.example.cleanarchitecture_toyproject.data.user;

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;
import io.reactivex.Observable;

import java.util.List;

interface UserRemoteSource {

    Observable<List<UserEntity>> getUsers(String userName);

}

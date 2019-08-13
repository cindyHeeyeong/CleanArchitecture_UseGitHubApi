package com.example.cleanarchitecture_toyproject.data.user

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel
import io.reactivex.Observable

interface UserCacheSource {

    //Insert Favorite User
    //Observable<UserEntity> setUsers(UserModel user);
    fun setUsers(userEntity: UserEntity)

    //Observable<UserModel> getUserList();
    fun getUserList(id: Int): UserEntity

    //select Favorite User
    fun selectUsers(): Observable<List<UserEntity>>

    //delete Favorite User
    fun deleteUsers(userEntity: UserEntity)
}

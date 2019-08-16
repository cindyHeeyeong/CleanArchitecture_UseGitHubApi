package com.example.cleanarchitecture_toyproject.data.user

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import io.reactivex.Observable

interface UserEntityCache {

    //Insert Favorite User
    fun setUsers(userEntity: UserEntity)

    //select all Users
    fun getUserList(id: Int): UserEntity

    //select Favorite User
    fun selectUsers(): Observable<List<UserEntity>>

    //delete Favorite User
    fun deleteUsers(userEntity: UserEntity)
}

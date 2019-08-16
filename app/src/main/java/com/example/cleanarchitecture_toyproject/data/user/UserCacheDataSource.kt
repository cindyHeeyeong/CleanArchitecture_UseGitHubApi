package com.example.cleanarchitecture_toyproject.data.user

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import io.reactivex.Observable

interface UserCacheDataSource {

    //db의 유저 정보 가져오기
    fun selectUsers(): Observable<List<UserEntity>>

    //Insert Favorite User
    fun setUsers(userEntity: UserEntity)

    //Delete Favorite User
    fun deleteUsers(userEntity: UserEntity)


}

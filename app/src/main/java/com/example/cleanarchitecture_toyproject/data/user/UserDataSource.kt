package com.example.cleanarchitecture_toyproject.data.user

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel
import io.reactivex.Observable

interface UserDataSource {
    //사용자 이름 검색 후 사용자 list 가져오기
    fun getUsers(userName: String): Observable<List<UserEntity>>

    //db의 유저 정보 가져오기
    fun selectUsers(): Observable<List<UserEntity>>

    //Insert Favorite User
    fun setUsers(userEntity: UserEntity)

    //Delete Favorite User
    fun deleteUsers(userEntity: UserEntity)


}

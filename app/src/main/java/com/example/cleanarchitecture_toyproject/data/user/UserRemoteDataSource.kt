package com.example.cleanarchitecture_toyproject.data.user

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import io.reactivex.Observable

interface UserRemoteDataSource {
    //사용자 이름 검색 후 사용자 list 가져오기
    fun getUsers(userName: String): Observable<List<UserEntity>>
}
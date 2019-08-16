package com.example.cleanarchitecture_toyproject.data.user

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import io.reactivex.Observable

interface UserRemote {

    fun getUsers(userName: String): Observable<List<UserEntity>>

}

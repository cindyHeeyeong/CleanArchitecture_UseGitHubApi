package com.example.cleanarchitecture_toyproject.data.net

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestApi {

    //user login name 불러오기
    fun getUsers(userName: String): Observable<List<UserEntity>>
}

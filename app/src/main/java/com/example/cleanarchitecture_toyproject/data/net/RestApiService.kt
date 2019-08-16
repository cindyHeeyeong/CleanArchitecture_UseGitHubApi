package com.example.cleanarchitecture_toyproject.data.net

import com.example.cleanarchitecture_toyproject.data.entity.GitHubEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApiService {

    //rest 통신을 하기 위한 인터페이스
    @GET("/search/users")
    fun getUserList(@Query("q") UserName: String): Observable<GitHubEntity>
}

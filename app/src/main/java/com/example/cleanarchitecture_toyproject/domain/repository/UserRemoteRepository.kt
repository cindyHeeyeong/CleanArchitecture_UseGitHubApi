package com.example.cleanarchitecture_toyproject.domain.repository

import com.example.cleanarchitecture_toyproject.domain.User
import io.reactivex.Observable

//TODO koin을 위해서 remote repository를 따로 만들어서함 -> 기능 별로 repository 따로 만들 예정
interface UserRemoteRepository {
    fun getUsers(userName: String): Observable<List<User>>
}
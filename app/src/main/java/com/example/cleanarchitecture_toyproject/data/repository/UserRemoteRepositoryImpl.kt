package com.example.cleanarchitecture_toyproject.data.repository

import android.util.Log
import com.example.cleanarchitecture_toyproject.data.mapper.UserEntityMapper
import com.example.cleanarchitecture_toyproject.data.user.UserRemoteDataSource
import com.example.cleanarchitecture_toyproject.domain.User
import com.example.cleanarchitecture_toyproject.domain.repository.UserRemoteRepository
import io.reactivex.Observable

class UserRemoteRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userEntityMapper: UserEntityMapper ) : UserRemoteRepository {

    override fun getUsers(userName: String): Observable<List<User>> {
        Log.d("UserRepositoryImpl", "getUsers")
        return userRemoteDataSource.getUsers(userName).map(userEntityMapper::transform)
    }

}
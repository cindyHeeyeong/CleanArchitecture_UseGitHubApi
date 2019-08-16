package com.example.cleanarchitecture_toyproject.data.user

import android.util.Log
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import io.reactivex.Observable

//UserCacheDataSourceImpl

class UserCacheDataSourceImpl (
    private val userEntityCache: UserEntityCache
) : UserCacheDataSource {

    override fun selectUsers(): Observable<List<UserEntity>> {
        return userEntityCache.selectUsers()
    }

    override fun setUsers(userEntity: UserEntity) {
        Log.d("usercachesource2", userEntity.getchecked().toString())
        userEntityCache.setUsers(userEntity)
    }

    override fun deleteUsers(userEntity: UserEntity) {
        userEntityCache.deleteUsers(userEntity)
    }

}
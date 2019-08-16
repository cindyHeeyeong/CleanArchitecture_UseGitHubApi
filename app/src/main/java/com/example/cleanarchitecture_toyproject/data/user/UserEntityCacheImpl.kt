package com.example.cleanarchitecture_toyproject.data.user

import android.util.Log
import com.example.cleanarchitecture_toyproject.data.cache.database.AppDatabase
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import io.reactivex.Observable

class UserEntityCacheImpl(private val database: AppDatabase) : UserEntityCache {


    override fun getUserList(id: Int): UserEntity {
        return database.userDao().loadIDUser(id)
    }

    override fun selectUsers(): Observable<List<UserEntity>> {
        Log.d("selectUsers", "selectUsers")
        return database.userDao().loadAllUser()
    }

    override fun deleteUsers(userEntity: UserEntity) {
        database.userDao().deleteUser(userEntity)
    }

    //remote에 있는 getuser불러오기
    override fun setUsers(userEntity: UserEntity) {
        Log.d("userCachesource123", userEntity.getchecked().toString())
        database.userDao().insert(userEntity)
    }
}
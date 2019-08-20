package com.example.cleanarchitecture_toyproject.data.mapper

import android.util.Log
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import com.example.cleanarchitecture_toyproject.domain.User

import java.util.ArrayList

class UserModelMapper {

    private fun transform(userModel: UserEntity): User {
        Log.d("debug700", userModel.getchecked().toString())
        return User(userModel.id, userModel.login, userModel.avatar_url, userModel.getchecked())
    }

    fun transform(userModels: List<UserEntity>): List<User> {
        val userList = ArrayList<User>()
        for (userModel in userModels) {
            val user = transform(userModel)
            if (user != null) {
                userList.add(user)
            }

        }
        return userList
    }

}

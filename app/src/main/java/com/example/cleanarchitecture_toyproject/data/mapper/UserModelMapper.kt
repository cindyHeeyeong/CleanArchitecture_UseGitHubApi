package com.example.cleanarchitecture_toyproject.data.mapper

import android.util.Log
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import com.example.cleanarchitecture_toyproject.domain.User

import java.util.ArrayList

class UserModelMapper {

    private fun transform(target : UserEntity): User = with(target){
        //Log.d("debug700", userModel.getchecked().toString())
        return User(id, login, avatar_url, checked)
    }

    fun transform(target : List<UserEntity>): List<User> = with(target) {
        val userList = ArrayList<User>()
        for (userModel in target) {
            val user = transform(userModel)
            if (user != null) {
                userList.add(user)
            }

        }
        return userList
    }

}

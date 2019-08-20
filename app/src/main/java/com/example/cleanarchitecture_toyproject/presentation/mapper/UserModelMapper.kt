package com.example.cleanarchitecture_toyproject.presentation.mapper

import android.util.Log
import com.example.cleanarchitecture_toyproject.domain.User
import com.example.cleanarchitecture_toyproject.presentation.model.UserModel

import java.util.ArrayList

//UserModelMapper
class UserModelMapper {

    private fun transform(user: User?): UserModel {
        if (user == null) {
            throw IllegalArgumentException("cannot transform a null value")
        }

        val userModel = UserModel(user.id, user.login, user.avatar_url, user.checked)

        userModel.login = user.login
        userModel.avatar_url = user.avatar_url
        Log.d("userModeldatamapper 1", userModel.login)
        Log.d("userModeldatamapper 2", userModel.avatar_url)
        Log.d("userModeldatamapper 3", userModel.checked.toString())
        return userModel
    }

    fun transform(userCollection: List<User>?): List<UserModel> {
        val userModelCollection: MutableList<UserModel>

        if (userCollection != null && !userCollection.isEmpty()) {
            userModelCollection = ArrayList()
            for (user in userCollection) {
                userModelCollection.add(transform(user))
            }
        } else {
            userModelCollection = emptyList<UserModel>() as MutableList<UserModel>
        }
        return userModelCollection
    }

    fun transform(userModel: UserModel): User {
        Log.d("User Transform", userModel.avatar_url.toString())
        return User(userModel.id, userModel.login, userModel.avatar_url, userModel.checked)
    }
}

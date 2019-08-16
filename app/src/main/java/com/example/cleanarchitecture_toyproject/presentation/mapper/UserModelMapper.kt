package com.example.cleanarchitecture_toyproject.presentation.mapper

import android.util.Log
import com.example.cleanarchitecture_toyproject.domain.User
import com.example.cleanarchitecture_toyproject.presentation.model.UserModel

import java.util.ArrayList

//UserEntityMapper
class UserModelMapper {

    fun transform(target: User): UserModel = with(target) {
        return UserModel(id, login, avatar_url, checked)
    }

    fun transform(target: List<User>): List<UserModel> = with(target) {
        return map { UserModel(it.id,it.login,it.avatar_url,it.checked) }
    }

    fun transform(target: UserModel): User  = with(target){
        return User(id, login, avatar_url, checked)
    }
}

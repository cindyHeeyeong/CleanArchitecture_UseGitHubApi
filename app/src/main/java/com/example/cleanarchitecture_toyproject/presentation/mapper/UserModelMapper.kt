package com.example.cleanarchitecture_toyproject.presentation.mapper

import android.util.Log
import com.example.cleanarchitecture_toyproject.domain.User
import com.example.cleanarchitecture_toyproject.presentation.model.UserModel

import java.util.ArrayList

//UserModelMapper
class UserModelMapper {

    //mapper에는 private function 만들지 않기
/*
    private fun transform(target: User): UserModel = with(target){
        if (target == null) {
            throw IllegalArgumentException("cannot transform a null value")
        }


        Log.d("userModeldatamapper 1", login)
        Log.d("userModeldatamapper 2", avatar_url)
        Log.d("userModeldatamapper 3", checked.toString())
        return UserModel(id, login, avatar_url, checked)
    }*/

    fun transform(target: List<User>): List<UserModel> = with(target){
        return map { UserModel(it.id, it.login, it.avatar_url, it.checked) }
    }

    fun transform(userModel: UserModel): User = with(userModel){
        Log.d("User Transform", userModel.avatar_url.toString())
        return User(id, login, avatar_url, checked)
    }
}

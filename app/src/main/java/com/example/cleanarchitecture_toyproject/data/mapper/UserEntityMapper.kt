package com.example.cleanarchitecture_toyproject.data.mapper

//(data)userEntity -> (domain)users mapping

import android.util.Log
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import com.example.cleanarchitecture_toyproject.domain.User
import java.util.ArrayList

class UserEntityMapper {

    //data layer에 있는 entity를 그대로 맵핑 시키지 않아도 된다.
    //domain layer에서 필요한 데이터만 맵핑시켜도 된다.

    //login, id, avatar_url
    //repository에서 사용 data, domain 의존성 끊어줌
    private fun transform(target: UserEntity): User {
        Log.d("transform", "transform")
        Log.d("transform2", target.getchecked().toString())
        return User(target.id, target.login, target.avatar_url, target.getchecked())
    }

    fun transform(userEntityCollection: List<UserEntity>): List<User> {
        val userList = ArrayList<User>(20)
        for (userEntity in userEntityCollection) {
            val user = transform(userEntity)
            userList.add(user)
        }
        return userList
    }

    fun transform(target: User): UserEntity {
        Log.d("debug124", target.checked.toString())
        return UserEntity(target.id, target.login, target.avatar_url, target.checked)
    }


}

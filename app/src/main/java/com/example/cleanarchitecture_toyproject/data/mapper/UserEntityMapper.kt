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

    // with : 반복되는 구문을 줄여주기 좋다.
    fun transform(target: User): UserEntity = with(target) {
        Log.d("transform", "transform")
        //Log.d("transform2", target.getchecked().toString())
        return UserEntity(login, id, avatar_url,checked)
    }

    fun transform(target: List<UserEntity>): List<User> = with(target){
       /* val userList = ArrayList<User>(20)
        for (userEntity in target) {
            var user = transform(userEntity)
            userList.add(user)
        }
        return userList*/
        return map { User(it.id, it.login, it.avatar_url, it.checked)}
    }

/*
     fun transform(target: User): UserEntity = with(target){
         Log.d("debug124", target.checked.toString())
         return UserEntity(id, login, avatar_url, checked)
     }*/


}

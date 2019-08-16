package com.example.cleanarchitecture_toyproject.data.mapper

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import com.example.cleanarchitecture_toyproject.domain.User


class UserEntityMapper {

    fun transform(target: User): UserEntity = with(target) {
        return UserEntity(id = id,login = login,avatar_url = avatar_url,checked = checked)
    }

    fun transform(target: List<UserEntity>): List<User> = with(target) {
        return map { User(it.id,it.login,it.avatar_url,it.checked) }
    }

}

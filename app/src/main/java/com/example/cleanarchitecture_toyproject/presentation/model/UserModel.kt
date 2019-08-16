package com.example.cleanarchitecture_toyproject.presentation.model


//TODO entity를 data class로 바꾸기
data class UserModel(val id: Int, val login: String, val avatar_url: String, var checked: Boolean)
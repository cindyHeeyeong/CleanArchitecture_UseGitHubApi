package com.example.cleanarchitecture_toyproject.presentation.ui

import com.example.cleanarchitecture_toyproject.presentation.model.UserModel

interface UserClickListener {
    fun setOnClick(userModel: UserModel)

}

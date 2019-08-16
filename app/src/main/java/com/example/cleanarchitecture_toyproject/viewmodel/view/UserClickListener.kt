package com.example.cleanarchitecture_toyproject.viewmodel.view

import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel

interface UserClickListener {
    fun setOnClick(userModel: UserModel)

}

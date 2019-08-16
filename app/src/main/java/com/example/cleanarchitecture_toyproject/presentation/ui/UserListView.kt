package com.example.cleanarchitecture_toyproject.presentation.ui

import android.view.View
import com.example.cleanarchitecture_toyproject.presentation.model.UserModel

interface UserListView : LoadDataView<View> {
    fun renderUserlist(userModelCollection: List<UserModel>)

}

package com.example.cleanarchitecture_toyproject.viewmodel.view

import android.view.View
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel

interface UserListView : LoadDataView<View> {
    fun renderUserlist(userModelCollection: List<UserModel>)

}

package com.example.cleanarchitecture_toyproject.viewmodel.model

import android.util.Log


//TODO entity를 data class로 바꾸기
class UserModel
//Checkbox checked


    (val id: Int, var login: String?, var avatar_url: String?, isChecked: Boolean?) {

    //checkbox setting
    var checked: Boolean? = null
        get() {
            Log.d("getChecked", field.toString())
            return field
        }

    init {
        this.checked = isChecked
    }

}

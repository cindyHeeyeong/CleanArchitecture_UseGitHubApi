package com.example.cleanarchitecture_toyproject.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//room과 동시에 json 사용
@Entity(tableName = "user")
data class UserEntity(

    //github login username
    //써야 할 것 : login, id, avatar_url
    @SerializedName("login")
    @ColumnInfo(name = "login")
    var login: String = "",

    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    var avatar_url: String = "",


    @ColumnInfo(name = "checked")
    var checked: Boolean = false

    //room과 rest하는 entity
    //room, json 을 entity에 같이 쓰기
    //@cloumninfo / @ignore
    /*constructor(id: Int, login: String?, avatar_url: String?, isChecked: Boolean?) {
        this.id = id
        this.login = login
        this.avatar_url = avatar_url
        this.checked = isChecked
    }

    fun getchecked(): Boolean? {
        return checked
    }*/


)

package com.example.cleanarchitecture_toyproject.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//room과 동시에 json 사용
@Entity(tableName = "user")
class UserEntity {

    //github login username
    //써야 할 것 : login, id, avatar_url
    @SerializedName("login")
    @ColumnInfo(name = "login")
    var login: String? = null

    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0

    @SerializedName("node_id")
    @Ignore
    var node_id: String? = null

    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    var avatar_url: String? = null

    @SerializedName("gravatar_id")
    @Ignore
    private var gravatar_id: String? = null

    @SerializedName("url")
    @Ignore
    var url: String? = null

    @SerializedName("html_url")
    @Ignore
    var hrml_url: String? = null

    @SerializedName("followers_url")
    @Ignore
    private var followers_url: String? = null

    @SerializedName("following_url")
    @Ignore
    var following_url: String? = null

    @SerializedName("gists_url")
    @Ignore
    var gists_url: String? = null

    @SerializedName("starred_url")
    @Ignore
    var starred_url: String? = null

    @SerializedName("subscriptions_url")
    @Ignore
    var subscriptions_url: String? = null

    @SerializedName("organizations_url")
    @Ignore
    var organizations_url: String? = null

    @SerializedName("repos_url")
    @Ignore
    var repos_url: String? = null

    @SerializedName("events_url")
    @Ignore
    var events_url: String? = null

    @SerializedName("received_events_url")
    @Ignore
    var received_events_url: String? = null

    @SerializedName("type")
    @Ignore
    var type: String? = null

    @SerializedName("site_admin")
    @Ignore
    var isSite_admin: Boolean = false

    @SerializedName("score")
    @Ignore
    var score: Float = 0.toFloat()

    @ColumnInfo(name = "checked")
    var checked: Boolean? = null

    //room과 rest하는 entity
    //room, json 을 entity에 같이 쓰기
    //@cloumninfo / @ignore
    constructor() {

    }

    constructor(id: Int, login: String?, avatar_url: String?, isChecked: Boolean?) {
        this.id = id
        this.login = login
        this.avatar_url = avatar_url
        this.checked = isChecked
    }

    fun getchecked(): Boolean? {
        return checked
    }

    fun getgravatar_id(): String? {
        return gravatar_id
    }

    fun setgravatar_id(gravatar_id: String) {
        this.gravatar_id = gravatar_id
    }

    fun getfollowers_url(): String? {
        return followers_url
    }

    fun setfollowers_url(followers_url: String) {
        this.followers_url = followers_url
    }


}

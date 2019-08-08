package com.example.cleanarchitecture_toyproject.data.cache.database;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;


//Dao Class
//이거 지워야 하는 듯?
public class FavoriteUser {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private final int id;

    @ColumnInfo(name = "login")
    private String login;

    @ColumnInfo(name = "avatar_url")
    private String avatar_url;

    public FavoriteUser(int id, String login, String avatar_url) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}

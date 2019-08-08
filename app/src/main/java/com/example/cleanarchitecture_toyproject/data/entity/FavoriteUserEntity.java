package com.example.cleanarchitecture_toyproject.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import com.example.cleanarchitecture_toyproject.data.cache.database.FavoriteUser;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;

@Entity(tableName = "user")
public class FavoriteUserEntity {

    @ColumnInfo(name = "favoriteUser")
    UserModel userModel;

    public FavoriteUserEntity(UserModel userModel) {
        this.userModel = userModel;
    }


    public UserModel getUser() {
        return userModel;
    }

    @ColumnInfo(name="login")
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @PrimaryKey
    private int id;

    @ColumnInfo(name="avatar_url")
    private String avatar_url;


    public static FavoriteUserEntity createNewTask(UserModel userModel) {
        return new FavoriteUserEntity(userModel);
    }

}

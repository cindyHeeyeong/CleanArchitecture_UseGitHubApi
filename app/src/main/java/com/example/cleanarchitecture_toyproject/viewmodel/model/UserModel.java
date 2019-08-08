package com.example.cleanarchitecture_toyproject.viewmodel.model;

import android.util.Log;
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;

public class UserModel {

    private int id;

    private String login;

    private String avatar_url;

    private Boolean isChecked = false;

    //checkbox setting
    public Boolean getChecked() {
        Log.d("getChecked", String.valueOf(isChecked));
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    //Checkbox checked


    public UserModel(int id, String login, String avatar_url, Boolean isChecked) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
        this.isChecked = isChecked;
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


    //생성자로 쓰기
    public static UserModel createNewTask(int id, String login, String avatar_url, Boolean isChecked) {
        return new UserModel(id, login, avatar_url, isChecked);
    }
}

/*
@Entity(tableName = "user")
public class UserModel {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "login")
    private String login;

    @ColumnInfo(name = "avatar_url")
    private String avatar_url;

    private Boolean isChecked = false;

    //checkbox setting
    public Boolean getChecked() {
        Log.d("getChecked", String.valueOf(isChecked));
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    //Checkbox checked


    public UserModel(int id, String login, String avatar_url, Boolean isChecked) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
        this.isChecked = isChecked;
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
    public static UserModel createNewTask(int id, String login, String avatar_url, Boolean isChecked) {
        return new UserModel(id, login, avatar_url, isChecked);
    }
}
*/

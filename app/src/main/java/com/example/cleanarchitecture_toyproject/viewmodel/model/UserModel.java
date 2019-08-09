package com.example.cleanarchitecture_toyproject.viewmodel.model;

import android.util.Log;

public class UserModel {

    private int id;

    private String login;

    private String avatar_url;

    private Boolean isChecked;

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

}

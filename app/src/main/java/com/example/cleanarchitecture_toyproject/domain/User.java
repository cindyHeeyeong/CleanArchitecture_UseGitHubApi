package com.example.cleanarchitecture_toyproject.domain;


public class User {

    private final int id;
    private String login;
    private String avatar_url;
    private Boolean checked;

    public User(int id, String login, String avatar_url, Boolean checked) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
        this.checked = checked;
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

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

}

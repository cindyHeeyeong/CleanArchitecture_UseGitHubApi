package com.example.cleanarchitecture_toyproject.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

//room과 동시에 json 사용
@Entity(tableName = "user")
public class UserEntity {
//room과 rest하는 entity
    //room, json 을 entity에 같이 쓰기
    //@cloumninfo / @ignore
    public UserEntity() {

    }

    public UserEntity(int id, String login, String avatar_url, Boolean isChecked) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
        this.checked = isChecked;
    }

    //github login username
    //써야 할 것 : login, id, avatar_url
    @SerializedName("login")
    @ColumnInfo(name = "login")
    private String login;

    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("node_id")
    @Ignore
    private String node_id;

    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    private String avatar_url;

    @SerializedName("gravatar_id")
    @Ignore
    private String gravatar_id;

    @SerializedName("url")
    @Ignore
    private String url;

    @SerializedName("html_url")
    @Ignore
    private String hrml_url;

    @SerializedName("followers_url")
    @Ignore
    private String followers_url;

    @SerializedName("following_url")
    @Ignore
    private String following_url;

    @SerializedName("gists_url")
    @Ignore
    private String gists_url;

    @SerializedName("starred_url")
    @Ignore
    private String starred_url;

    @SerializedName("subscriptions_url")
    @Ignore
    private String subscriptions_url;

    @SerializedName("organizations_url")
    @Ignore
    private String organizations_url;

    @SerializedName("repos_url")
    @Ignore
    private String repos_url;

    @SerializedName("events_url")
    @Ignore
    private String events_url;

    @SerializedName("received_events_url")
    @Ignore
    private String received_events_url;

    @SerializedName("type")
    @Ignore
    private String type;

    @SerializedName("site_admin")
    @Ignore
    private boolean site_admin;

    @SerializedName("score")
    @Ignore
    private float score;

    public Boolean getChecked() {
        return checked;
    }

    @ColumnInfo(name = "checked")
    private Boolean checked;


    public boolean isSite_admin() {
        return site_admin;
    }

    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }


    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getchecked() {
        return checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getgravatar_id() {
        return gravatar_id;
    }

    public void setgravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHrml_url() {
        return hrml_url;
    }

    public void setHrml_url(String hrml_url) {
        this.hrml_url = hrml_url;
    }

    public String getfollowers_url() {
        return followers_url;
    }

    public void setfollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }


}

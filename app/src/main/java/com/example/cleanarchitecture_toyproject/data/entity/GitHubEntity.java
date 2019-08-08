package com.example.cleanarchitecture_toyproject.data.entity;

import android.util.Log;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GitHubEntity {

    @SerializedName("total_count")
    private String total_count;
    @SerializedName("incomplete_results")
    private String incomplete_results;
    @SerializedName("items")
    private List<UserEntity> itemsList;

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public String getIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(String incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public List<UserEntity> getList() {
        return itemsList;
    }

    public void setList(ArrayList<UserEntity> itemsList){
        this.itemsList = itemsList;
    }

}

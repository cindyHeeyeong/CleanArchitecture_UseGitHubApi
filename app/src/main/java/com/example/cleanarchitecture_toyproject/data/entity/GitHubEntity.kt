package com.example.cleanarchitecture_toyproject.data.entity

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class GitHubEntity {

    @SerializedName("total_count")
    var total_count: String? = null
    @SerializedName("incomplete_results")
    var incomplete_results: String? = null
    @SerializedName("items")
    var list: List<UserEntity>? = null
        private set

    fun setList(itemsList: ArrayList<UserEntity>) {
        this.list = itemsList
    }

}

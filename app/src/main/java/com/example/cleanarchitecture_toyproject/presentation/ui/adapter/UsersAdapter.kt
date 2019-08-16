package com.example.cleanarchitecture_toyproject.presentation.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cleanarchitecture_toyproject.R
import com.example.cleanarchitecture_toyproject.presentation.extenstions.getItem
import com.example.cleanarchitecture_toyproject.presentation.extenstions.loadUrl
import com.example.cleanarchitecture_toyproject.presentation.model.UserModel
import com.example.cleanarchitecture_toyproject.presentation.ui.UserClickListener
import java.util.ArrayList

class UsersAdapter(userList: ArrayList<UserModel>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    private var usersList: List<UserModel>? = null
    private var listener: UserClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        usersList?.let {
            with(holder){
                it.getItem(position)?.let {item->
                    checkBox.isChecked = item.checked
                    username.text = item.login
                    user_profile_avatar.loadUrl(item.avatar_url)
                    itemView.setOnClickListener { view ->
                        if (listener != null) {
                            Log.d("userModel", "favorite$item")
                            //TODO 이 구문 고쳐야함
                            item.checked = !item.checked
                            Log.d("Adapter", "check" + item.checked)
                            //userModel.setChecked(true);
                            checkBox.isChecked = item.checked
                            Log.d("UsersAdapter", item.checked.toString())

                            listener?.setOnClick(item)

                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return usersList!!.size
    }


    init {
        this.usersList = userList
    }

    //presenter에서 받아온 데이터 setting
    fun setUserCollection(usersList: List<UserModel>) {
        this.usersList = usersList
    }

    fun setOnItemClickListener(listener: UserClickListener) {
        this.listener = listener
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var user_profile_avatar: ImageView
        var username: TextView
        var checkBox: CheckBox

        init {
            user_profile_avatar = view.findViewById<View>(R.id.avatar_img) as ImageView
            username = view.findViewById<View>(R.id.userName) as TextView
            checkBox = view.findViewById<View>(R.id.favorite_btn) as CheckBox
        }
    }

}

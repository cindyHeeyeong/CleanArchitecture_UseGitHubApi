package com.example.cleanarchitecture_toyproject.viewmodel.view.adapter

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
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserClickListener
import java.util.ArrayList

class UsersAdapter(userList: ArrayList<UserModel>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    private var usersList: List<UserModel>? = null
    private var listener: UserClickListener? = null

    private val userModel1: UserModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)
        return UserViewHolder(itemView)
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var user_profile_avatar: ImageView
        internal var username: TextView
        internal var checkBox: CheckBox

        init {
            user_profile_avatar = view.findViewById<View>(R.id.avatar_img) as ImageView
            username = view.findViewById<View>(R.id.userName) as TextView
            checkBox = view.findViewById<View>(R.id.favorite_btn) as CheckBox
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userModel = usersList!![position]
        //UserModel userModel = new UserModel(userModel1.getId(), userModel1.getLogin(), userModel1.getAvatar_url(), userModel1.getChecked());
        if (userModel.checked != null) {
            holder.checkBox.isChecked = userModel.checked!!
        }
        holder.username.text = userModel.login

        holder.itemView.setOnClickListener { view ->
            if (listener != null) {
                Log.d("userModel", "favorite$userModel")
                //TODO 이 구문 고쳐야함
                userModel.checked = !userModel.checked!!
                Log.d("Adapter", "check" + userModel.checked!!)
                //userModel.setChecked(true);
                holder.checkBox.isChecked = userModel.checked!!
                Log.d("UsersAdapter", userModel.checked.toString())

                listener!!.setOnClick(userModel)

            }
        }

        //glide를 사용하여 이미지 로딩
        val context = holder.user_profile_avatar.context
        Glide.with(context).load(userModel.avatar_url).into(holder.user_profile_avatar)
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

}

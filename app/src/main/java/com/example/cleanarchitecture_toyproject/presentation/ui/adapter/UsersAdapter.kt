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
import com.example.cleanarchitecture_toyproject.presentation.model.UserModel
import com.example.cleanarchitecture_toyproject.presentation.ui.UserClickListener
import com.example.cleanarchitecture_toyproject.presentation.ui.extensions.getItem
import com.example.cleanarchitecture_toyproject.presentation.ui.extensions.loadUrl
import java.util.ArrayList

class UsersAdapter(userList: ArrayList<UserModel>) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {
    private var userList2: ArrayList<UserModel>? = null
    private var usersList: List<UserModel>? = null
    private var listener: UserClickListener? = null

    private var position: Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)
        return UserViewHolder(itemView)
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

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        usersList?.getItem(position)?.let { userModel ->
            //glide를 사용하여 이미지 로딩
            val context = holder.user_profile_avatar.context
            Glide.with(context).load(userModel.avatar_url).into(holder.user_profile_avatar)
            with(holder) {
                checkBox.isChecked = userModel.checked
                username.text = userModel.login
                user_profile_avatar.loadUrl(userModel.avatar_url)

                itemView.setOnClickListener { view ->
                    listener?.let {
                        this@UsersAdapter.position = position
                        userModel.checked = !userModel.checked
                        checkBox.isChecked = userModel.checked

                        listener?.setOnClick(userModel)
                    }
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return usersList!!.size
    }

    fun removeItem(userModel: UserModel) {
        Log.d("removeItem2", "->$position")
        userList2?.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    init {
        this.usersList = userList
        this.userList2 = userList
    }

    //presenter에서 받아온 데이터 setting
    fun setUserCollection(usersList: List<UserModel>) {
        this.usersList = usersList
    }

    fun setOnItemClickListener(listener: UserClickListener) {
        this.listener = listener
    }

}

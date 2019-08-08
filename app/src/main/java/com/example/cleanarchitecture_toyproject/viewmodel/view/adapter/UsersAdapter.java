package com.example.cleanarchitecture_toyproject.viewmodel.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.cleanarchitecture_toyproject.R;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserClickListener;
import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    private List<UserModel> usersList;
    private UserClickListener listener = null;

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new UserViewHolder(itemView);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        ImageView user_profile_avatar;
        TextView username;
        CheckBox checkBox;

        public UserViewHolder(View view) {
            super(view);
            user_profile_avatar = (ImageView) view.findViewById(R.id.avatar_img);
            username = (TextView) view.findViewById(R.id.userName);
            checkBox = (CheckBox) view.findViewById(R.id.favorite_btn);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserModel userModel = usersList.get(position);
        if(userModel.getChecked() != null) {
            holder.checkBox.setChecked(userModel.getChecked());
        }
        holder.username.setText(userModel.getLogin());

        holder.itemView.setOnClickListener(view -> {
            if(listener != null){
                    Log.d("userModel","favorite" +String.valueOf(userModel));
                    userModel.setChecked(!userModel.getChecked());
                    Log.d("Adapter", "check" + userModel.getChecked());
                    //userModel.setChecked(true);
                    holder.checkBox.setChecked(userModel.getChecked());
                    Log.d("UsersAdapter", String.valueOf(userModel.getChecked()));

                    listener.setOnClick(userModel);

            }
        });

        //glide를 사용하여 이미지 로딩
        Context context = holder.user_profile_avatar.getContext();
        Glide.with(context).load(userModel.getAvatar_url()).into(holder.user_profile_avatar);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }


    public UsersAdapter(ArrayList<UserModel> userList) {
        this.usersList = userList;
    }

    //presenter에서 받아온 데이터 setting
    public void setUserCollection(List<UserModel> usersList){
        this.usersList = usersList;
    }

    public void setOnItemClickListener(UserClickListener listener){
        this.listener = listener;
    }
}

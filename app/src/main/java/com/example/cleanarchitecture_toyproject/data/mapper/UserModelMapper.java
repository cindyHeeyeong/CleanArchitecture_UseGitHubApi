package com.example.cleanarchitecture_toyproject.data.mapper;

import android.util.Log;
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;
import com.example.cleanarchitecture_toyproject.domain.User;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserModelMapper {

    private User transform(UserEntity userModel) {
        Log.d("debug700", String.valueOf(userModel.getchecked()));
        return new User(userModel.getId(), userModel.getLogin(), userModel.getAvatar_url(), userModel.getchecked());
    }

    public List<User> transform(List<UserEntity> userModels) {
        List<User> userList = new ArrayList<>();
        for (UserEntity userModel : userModels) {
            User user = transform(userModel);
            if (user != null) {
                userList.add(user);
            }

        }
        return userList;
    }

}

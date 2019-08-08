package com.example.cleanarchitecture_toyproject.viewmodel.mapper;

import android.util.Log;
import com.example.cleanarchitecture_toyproject.domain.User;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//UserModelMapper
public class UserModelMapper {
    public UserModelMapper() {
    }

    private UserModel transform(User user) {
        if (user == null) {
            throw new IllegalArgumentException("cannot transform a null value");
        }
        UserModel userModel = new UserModel(user.getId(), user.getLogin(), user.getAvatar_url(), user.getChecked());

        userModel.setLogin(user.getLogin());
        userModel.setAvatar_url(user.getAvatar_url());
        Log.d("userModeldatamapper 1", userModel.getLogin());
        Log.d("userModeldatamapper 2", userModel.getAvatar_url());
        Log.d("userModeldatamapper 3", String.valueOf(userModel.getChecked()));
        return userModel;
    }

    public List<UserModel> transform(List<User> userCollection) {
        List<UserModel> userModelCollection;

        if (userCollection != null && !userCollection.isEmpty()) {
            userModelCollection = new ArrayList<>();
            for (User user : userCollection) {
                userModelCollection.add(transform(user));
            }
        } else {
            userModelCollection = Collections.emptyList();
        }
        return userModelCollection;
    }

    public User transform(UserModel userModel) {
        return new User(userModel.getId(), userModel.getLogin(), userModel.getAvatar_url(), userModel.getChecked());
    }
}

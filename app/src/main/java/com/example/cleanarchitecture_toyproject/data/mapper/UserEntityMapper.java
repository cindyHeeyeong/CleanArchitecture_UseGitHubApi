package com.example.cleanarchitecture_toyproject.data.mapper;

//(data)userEntity -> (domain)users mapping

import android.util.Log;
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;
import com.example.cleanarchitecture_toyproject.domain.User;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
import java.util.ArrayList;
import java.util.List;

public class UserEntityMapper {

    //data layer에 있는 entity를 그대로 맵핑 시키지 않아도 된다.
    //domain layer에서 필요한 데이터만 맵핑시켜도 된다.

    //login, id, avatar_url
    //repository에서 사용 data, domain 의존성 끊어줌
    private User transform(UserEntity target) {
        Log.d("transform", "transform");
        Log.d("transform2", String.valueOf(target.getchecked()));
        return new User(target.getId(),target.getLogin(),target.getAvatar_url(), target.getchecked());
    }

    public List<User> transform(List<UserEntity> userEntityCollection) {
        final List<User> userList = new ArrayList<>(20);
        for(UserEntity userEntity : userEntityCollection) {
            final User user = transform(userEntity);
            userList.add(user);
        }
        return userList;
    }

    public UserEntity transform(User target) {
        return new UserEntity(target.getId(),target.getLogin(),target.getAvatar_url(), target.getChecked());
    }


}

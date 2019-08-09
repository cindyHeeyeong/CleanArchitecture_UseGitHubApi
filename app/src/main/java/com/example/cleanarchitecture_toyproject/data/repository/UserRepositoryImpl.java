package com.example.cleanarchitecture_toyproject.data.repository;

import android.util.Log;
import com.example.cleanarchitecture_toyproject.data.mapper.UserEntityMapper;
import com.example.cleanarchitecture_toyproject.data.mapper.UserModelMapper;
import com.example.cleanarchitecture_toyproject.data.user.UserDataSource;
import com.example.cleanarchitecture_toyproject.data.user.UserDatasourceImpl;
import com.example.cleanarchitecture_toyproject.domain.repository.UserRepository;
import com.example.cleanarchitecture_toyproject.domain.User;
import io.reactivex.Observable;
import java.util.List;

//해당 기능에 관련된 repository / repository는 하나의 데이터만 컨트롤 하게/ 분리되는건 다 분리시키기
//UserRepository, FavoriteUserRepository

//Cache -> (interface)UserCache, favoriteUserCache, -> UserCacheImpl, favoriteUserCacheImpl
public class UserRepositoryImpl implements UserRepository {

    private static UserRepositoryImpl instance;
    private UserDataSource userDataSource;

    private UserEntityMapper userEntityMapper;
    private UserModelMapper userModelMapper;

    public UserRepositoryImpl(UserDataSource userDataSource, UserEntityMapper userEntityMapper, UserModelMapper userModelMapper){
        this.userDataSource = userDataSource;
        this.userEntityMapper = userEntityMapper;
        this.userModelMapper = userModelMapper;
    }

    //userRepository -> userdatasource
    public static UserRepositoryImpl getInstance(){
        if(instance == null) {
            synchronized (UserRepositoryImpl.class) {
                if(instance == null){
                    instance = new UserRepositoryImpl(UserDatasourceImpl.getInstance(), new UserEntityMapper(), new UserModelMapper());
                }
            }
        }
        return instance;
    }

    //datasource로 부터 의존성 주입 받기
    @Override
    public Observable<List<User>> getUsers(String userName) {
        Log.d("UserRepositoryImpl", "getUsers");
        return userDataSource.getUsers(userName).map(this.userEntityMapper::transform);
    }

    //db에서 favorite user정보를 가져온다.
    /***
     *
     *  return userDataSource.selectUsers().map(new Function<List<UserEntity>, List<User>>() {
     *             @Override
     *             public List<User> apply(List<UserEntity> userModels) throws Exception {
     *                 return userModelMapper.transform(userModels);
     *             }
     *         });
     *
     * ***/
    @Override
    public Observable<List<User>> selectUsers()
    {
        return userDataSource.selectUsers().map(userModels -> userModelMapper.transform(userModels));
    }

    @Override
    //usermodel-> userentity
    public void setUsers(User user) {
        Log.d("debug123", String.valueOf(user.getChecked()));
        userDataSource.setUsers(userEntityMapper.transform(user));
    }

    @Override
    public void deleteUsers(User user) {
        userDataSource.deleteUsers(userEntityMapper.transform(user));

    }
}

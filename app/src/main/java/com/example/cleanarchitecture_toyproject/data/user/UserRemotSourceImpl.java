package com.example.cleanarchitecture_toyproject.data.user;

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;
import com.example.cleanarchitecture_toyproject.data.net.RestApi;
import com.example.cleanarchitecture_toyproject.data.net.RestApiImpl;
import io.reactivex.Observable;

import java.util.List;

//UserRemoteSourceImpl
public class UserRemotSourceImpl implements UserRemoteSource {
    private static UserRemotSourceImpl userRemotSource;
    private final RestApi restApi;

    private UserRemotSourceImpl(RestApi restApi){
        this.restApi = restApi;
    }

    @Override
    public Observable<List<UserEntity>> getUsers(String userName) {
        return restApi.getUsers(userName);
    }


    //retrofitImpl에 의존성을 주입받는다.
    //singleton으로 인스턴스 생성
    public static UserRemoteSource getInstance(){
        if(userRemotSource == null) {
            synchronized (UserRemotSourceImpl.class) {
                if(userRemotSource == null) {
                    userRemotSource = new UserRemotSourceImpl(RestApiImpl.getInstance());
                }
            }
        }
        return userRemotSource;
    }

}

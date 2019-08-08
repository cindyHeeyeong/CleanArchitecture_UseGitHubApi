package com.example.cleanarchitecture_toyproject.data.net;

import android.util.Log;
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;
import com.example.cleanarchitecture_toyproject.data.mapper.UserEntityMapper;
import com.example.cleanarchitecture_toyproject.data.exception.NetworkConnectionException;
import io.reactivex.*;
import retrofit2.Retrofit;
import java.util.ArrayList;
import java.util.List;

public class RestApiImpl implements RestApi {

    private Retrofit retrofit;
    private static RestApiImpl restApiImpl;
    private RestApiService restApiService;

    ///retrofit 응답
    public String response;
    public ArrayList<UserEntity> userEntity;

    private UserEntityMapper userEntityMapper;

    private RestApiImpl(RestApiService restApiService) {
        this.restApiService = restApiService;
    }

    //@Singleton annotation이 있는건 생성자에 의존성 주입을 해준다
    public static RestApi getInstance() {
        if (restApiImpl == null) {
            synchronized (RestApiImpl.class) {
                if (restApiImpl == null) {
                    restApiImpl = new RestApiImpl(new RetrofitServiceCreator().getApiService(API_BASE_URL));
                }
            }
        }
        return restApiImpl;
    }

    @Override
    public Observable<List<UserEntity>> getUsers(String userName) {
        Log.d("RestApiImpl", userName);
        return Observable.create(emitter -> {
            try {
                emitter.onNext(restApiService.getUserList(userName).blockingSingle().getList());
                emitter.onComplete();
                Log.d("restapiImpl", "onComplete");
            } catch (Exception e) {
                emitter.onError(new NetworkConnectionException(e.getCause()));
            }
        });
    }
}

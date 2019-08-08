package com.example.cleanarchitecture_toyproject.data.user;

import android.util.Log;
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.List;

//UserDataSourceImpl

public class UserDatasourceImpl implements UserDataSource {

    private static UserDatasourceImpl instatnce;
    private UserRemoteSource userRemoteSource;
    private UserCacheSource userCacheSource;

    private UserDatasourceImpl(UserRemoteSource userRemoteSource, UserCacheSource userCacheSource){
        this.userRemoteSource = userRemoteSource;
        this.userCacheSource = userCacheSource;
    }

    //flatmap : observable의 데이터를 가공해서 다시 observable데이터로 만드는

    //userentity가 다 온 상태

    //비지니스 로직 자체를 처리하기 위해 사용
    //변환 연산자는 만들어진 데이터 흐름을 원하는 대로 변형할 수 있다.
    //기본이 되는 함수(map(), flatMap())와 비교하여 어떻게 다른지 그 차이점을 기억하는게 좋음.
    @Override
    public Observable<List<UserEntity>> getUsers(String userName) {
        //return userRemoteSource.getUsers(userName);
        return userRemoteSource.getUsers(userName)
                .flatMapIterable((Function<List<UserEntity>, Iterable<UserEntity>>) list -> list)
                .doOnNext(new Consumer<UserEntity>() { //observable이 item을 발행할 때 호출되는 콜백 함수
                    @Override
                    public void accept(UserEntity userEntity) throws Exception {
                        Log.d("userEntity", String.valueOf(userEntity));
                        //디비에 즐겨찾기 상태 가져와서 업데이트
                        //checkId 값 확인 후
                        //boolean값이 있는지 확인 후 return userentity;
                        //--> 이런 식으로 짜기 : userEntity.setChecked(userCacheSource.getUser(userEntity.getId()))
                        UserEntity userModel = userCacheSource.getUserList(userEntity.getId());
                        Log.v("DEBUG200","userModel :"+ userEntity.getId());

                        if(userModel != null){
                            Log.v("DEBUG100","userModel :"+ userEntity.getId());
                            userEntity.setChecked(true);
                        }
                        else if(userModel == null) {
                            userEntity.setChecked(false);
                        }

                    }
                })
                .toList().toObservable();
                //toList는 data를  list형태로 바꾸기 바꾸기
    }

    @Override
    public Observable<List<UserEntity>> selectUsers() {
        return userCacheSource.selectUsers();
    }

    @Override
    public void setUsers(UserEntity userEntity) {
        userCacheSource.setUsers(userEntity);
    }

    @Override
    public void deleteUsers(UserEntity userEntity) {
        userCacheSource.deleteUsers(userEntity);
    }

    //lazy double checking
    //userRemoteSourceImpl을 의존성 주입한다.
    public static UserDataSource getInstance(){
        if(instatnce == null){
            synchronized (UserDatasourceImpl.class){
                if(instatnce == null){
                    instatnce = new UserDatasourceImpl(UserRemotSourceImpl.getInstance(), UserCacheSourceImpl.getInstance());
                }
            }
        }
        return instatnce;
    }
}

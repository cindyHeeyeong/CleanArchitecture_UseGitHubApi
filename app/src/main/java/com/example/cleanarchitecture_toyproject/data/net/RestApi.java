package com.example.cleanarchitecture_toyproject.data.net;

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RestApi {

    //https://api.github.com/search/users?q=cindy
    //String API_BASE_URL = "https://api.github.com/search/users"; //TODO q=username도 추가

    String API_BASE_URL = "https://api.github.com/";

    //user login name 불러오기
    Observable<List<UserEntity>> getUsers(String userName);
}

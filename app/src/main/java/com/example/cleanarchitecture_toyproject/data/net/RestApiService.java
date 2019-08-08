package com.example.cleanarchitecture_toyproject.data.net;

import com.example.cleanarchitecture_toyproject.data.entity.GitHubEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiService {

    //rest 통신을 하기 위한 인터페이스
/*
    @GET("/search/users?q=cindy")
    Observable<GitHubEntity> getUserList(String UserName);
*/
    @GET("/search/users")
    Observable<GitHubEntity> getUserList(@Query("q") String UserName);

    //전체 유저로 가져와서 -> recyclerview에서 20명씩 불러오기, 그 다음 스크롤이 내려가면
    //다음 유저 불러오기
}

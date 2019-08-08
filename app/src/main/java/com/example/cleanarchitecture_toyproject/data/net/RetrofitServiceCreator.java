package com.example.cleanarchitecture_toyproject.data.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceCreator {
    private static RetrofitServiceCreator retrofitServiceCreator;

    //singleton으로 만들기
    //retrofit 객체는 한번만 생성되어야 한다.
    public static synchronized RetrofitServiceCreator getInstance() {
        if (retrofitServiceCreator == null) {
            synchronized (RetrofitServiceCreator.class) {
                retrofitServiceCreator = new RetrofitServiceCreator();
            }
        }
        return retrofitServiceCreator;
    }

    private Retrofit getRetrofit(String url) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit
                = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }


    public RestApiService getApiService(String url) {
        return getRetrofit(url).create(RestApiService.class);
    }
}

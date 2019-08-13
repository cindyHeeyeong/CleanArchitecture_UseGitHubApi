package com.example.cleanarchitecture_toyproject.data.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceCreator {

    private fun getRetrofit(url: String): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }


    fun getApiService(url: String): RestApiService {
        return getRetrofit(url).create(RestApiService::class.java)
    }

    companion object {
        private var retrofitServiceCreator: RetrofitServiceCreator? = null

        //singleton으로 만들기
        //retrofit 객체는 한번만 생성되어야 한다.
        val instance: RetrofitServiceCreator?
            @Synchronized get() {
                if (retrofitServiceCreator == null) {
                    synchronized(RetrofitServiceCreator::class.java) {
                        retrofitServiceCreator = RetrofitServiceCreator()
                    }
                }
                return retrofitServiceCreator
            }
    }
}

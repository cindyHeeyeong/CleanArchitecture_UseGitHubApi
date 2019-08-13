package com.example.cleanarchitecture_toyproject.data.net

import android.util.Log
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import com.example.cleanarchitecture_toyproject.data.mapper.UserEntityMapper
import com.example.cleanarchitecture_toyproject.data.exception.NetworkConnectionException
import io.reactivex.*
import java.util.ArrayList

class RestApiImpl private constructor(private val restApiService: RestApiService) : RestApi {

    ///retrofit 응답
    var response: String? = null
    var userEntity: ArrayList<UserEntity>? = null

    private val userEntityMapper: UserEntityMapper? = null

    override fun getUsers(userName: String): Observable<List<UserEntity>> {
        Log.d("RestApiImpl", userName)
        return Observable.create { emitter ->
            try {
                restApiService.getUserList(userName).blockingSingle().list?.let { emitter.onNext(it) }
                emitter.onComplete()
                Log.d("restapiImpl", "onComplete")
            } catch (e: Exception) {
                emitter.onError(NetworkConnectionException(e.cause))
            }
        }
    }

    companion object {

        private var restApiImpl: RestApiImpl? = null

        //@Singleton annotation이 있는건 생성자에 의존성 주입을 해준다
        val instance: RestApiImpl?
            get() {
                if (restApiImpl == null) {
                    synchronized(RestApiImpl::class.java) {
                        if (restApiImpl == null) {
                            restApiImpl =
                                RestApiImpl(RetrofitServiceCreator().getApiService(RestApi.Companion.API_BASE_URL))
                        }
                    }
                }
                return restApiImpl
            }
    }
}

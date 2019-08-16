package com.example.cleanarchitecture_toyproject.data.user

import android.util.Log
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import com.example.cleanarchitecture_toyproject.data.exception.NetworkConnectionException
import com.example.cleanarchitecture_toyproject.data.net.RestApiService
import io.reactivex.Observable

//UserRemoteSourceImpl
class UserRemotImpl(private val restApi: RestApiService) : UserRemote {

    override fun getUsers(userName: String): Observable<List<UserEntity>> {
            return Observable.create { emitter ->
                try {
                    restApi.getUserList(userName).blockingSingle().list?.let { emitter.onNext(it) }
                    emitter.onComplete()
                    Log.d("restapiImpl", "onComplete")
                } catch (e: Exception) {
                    emitter.onError(NetworkConnectionException(e.cause))
                }
            }
        }
    }



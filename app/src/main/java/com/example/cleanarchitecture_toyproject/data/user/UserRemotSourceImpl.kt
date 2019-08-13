package com.example.cleanarchitecture_toyproject.data.user

import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import com.example.cleanarchitecture_toyproject.data.net.RestApiImpl
import io.reactivex.Observable

//UserRemoteSourceImpl
class UserRemotSourceImpl private constructor(private val restApi: RestApiImpl?) : UserRemoteSource {

    override fun getUsers(userName: String): Observable<List<UserEntity>> {
        return restApi?.getUsers(userName)!!
    }

    companion object {
        private var userRemotSource: UserRemotSourceImpl? = null


        //retrofitImpl에 의존성을 주입받는다.
        //singleton으로 인스턴스 생성
        @JvmStatic val instance: UserRemoteSource?
            get() {
                if (userRemotSource == null) {
                    synchronized(UserRemotSourceImpl::class.java) {
                        if (userRemotSource == null) {
                            userRemotSource = UserRemotSourceImpl(RestApiImpl.instance)
                        }
                    }
                }
                return userRemotSource
            }
    }

}

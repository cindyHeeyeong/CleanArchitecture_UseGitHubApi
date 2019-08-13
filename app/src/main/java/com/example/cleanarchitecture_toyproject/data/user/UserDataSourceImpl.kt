package com.example.cleanarchitecture_toyproject.data.user

import android.util.Log
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import io.reactivex.Observable
import io.reactivex.functions.Function

//UserDataSourceImpl

class UserDataSourceImpl private constructor(
    private val userRemoteSource: UserRemoteSource,
    private val userCacheSource: UserCacheSource
) : UserDataSource {

    //flatmap : observable의 데이터를 가공해서 다시 observable데이터로 만드는

    //userentity가 다 온 상태

    //비지니스 로직 자체를 처리하기 위해 사용
    //변환 연산자는 만들어진 데이터 흐름을 원하는 대로 변형할 수 있다.
    //기본이 되는 함수(map(), flatMap())와 비교하여 어떻게 다른지 그 차이점을 기억하는게 좋음.
    override fun getUsers(userName: String): Observable<List<UserEntity>> {
        //return userRemoteSource.getUsers(userName);
        return userRemoteSource.getUsers(userName)
            .flatMapIterable({ list->list }/* as Function<List<UserEntity>, Iterable<UserEntity>>*/)
            .doOnNext { userEntity ->
                //observable이 item을 발행할 때 호출되는 콜백 함수
                Log.d("userEntity 333", userEntity.toString())
                //디비에 즐겨찾기 상태 가져와서 업데이트
                //checkId 값 확인 후
                //boolean값이 있는지 확인 후 return userentity;
                //--> 이런 식으로 짜기 : userEntity.setChecked(userCacheSource.getUser(userEntity.getId()))
                val userModel = userCacheSource.getUserList(userEntity.id)
                Log.v("DEBUG200", "userModel :" + userEntity.id)

                if (userModel != null) {
                    Log.v("DEBUG100", "userModel :" + userEntity.id)
                    userEntity.checked = true
                } else if (userModel == null) {
                    userEntity.checked = false
                }
            }
            .toList().toObservable()
        //toList는 data를  list형태로 바꾸기 바꾸기
    }

    override fun selectUsers(): Observable<List<UserEntity>> {
        return userCacheSource.selectUsers()
    }

    override fun setUsers(userEntity: UserEntity) {
        Log.d("usercachesource2", userEntity.getchecked().toString())
        userCacheSource.setUsers(userEntity)
    }

    override fun deleteUsers(userEntity: UserEntity) {
        userCacheSource.deleteUsers(userEntity)
    }

    companion object {

        private var instatnce: UserDataSourceImpl? = null

        //lazy double checking
        //userRemoteSourceImpl을 의존성 주입한다.
        val instance: UserDataSource?
            get() {
                if (instatnce == null) {
                    synchronized(UserDataSourceImpl::class.java) {
                        if (instatnce == null) {
                            instatnce = UserDataSourceImpl(UserRemotSourceImpl.instance!!,
                                UserCacheSourceImpl.instance!!
                            )
                            Log.d("userDatasource 1", UserRemotSourceImpl.instance.toString())
                            Log.d("userDatasource 2", UserCacheSourceImpl.instance.toString())
                        }
                    }
                }
                return instatnce
            }
    }
}
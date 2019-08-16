package com.example.cleanarchitecture_toyproject.data.user

import android.util.Log
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import io.reactivex.Observable

class UserRemoteDataSourceImpl(private val userRemote: UserRemote,
                               private val userEntityCache: UserEntityCache) : UserRemoteDataSource {
   override fun getUsers(userName: String): Observable<List<UserEntity>> {
       //return userRemote.getUsers(userName);
       return userRemote.getUsers(userName)
           .flatMapIterable({ list->list } )
           .doOnNext { userEntity ->
               //observable이 item을 발행할 때 호출되는 콜백 함수
               Log.d("userEntity 333", userEntity.toString())
               //디비에 즐겨찾기 상태 가져와서 업데이트
               //checkId 값 확인 후
               //boolean값이 있는지 확인 후 return userentity;
               //--> 이런 식으로 짜기 : userEntity.setChecked(userEntityCache.getUser(userEntity.getId()))
               val userModel = userEntityCache.getUserList(userEntity.id)
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
}
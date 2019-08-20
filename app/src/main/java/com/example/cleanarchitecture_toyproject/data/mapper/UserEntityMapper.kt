package com.example.cleanarchitecture_toyproject.data.mapper

//(data)userEntity -> (domain)users mapping

import android.util.Log
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import com.example.cleanarchitecture_toyproject.domain.User

class UserEntityMapper {

    //data layer에 있는 entity를 그대로 맵핑 시키지 않아도 된다.
    //domain layer에서 필요한 데이터만 맵핑시켜도 된다.

    //login, id, avatar_url
    //repository에서 사용 data, domain 의존성 끊어줌

    // with : 반복되는 구문을 줄여주기 좋다.
    //target.id, target.avatar.urlm target.login -> target. 을 줄여준다.
    fun transform(target: User): UserEntity = with(target) {
        Log.d("transform", "transform")
        //Log.d("transform2", target.getchecked().toString())
        return UserEntity(login, id, avatar_url, checked)
    }

    fun transform(target: List<UserEntity>): List<User> = with(target) {
        /*
        public inline fun <T, R, C : MutableCollection<in R>> Iterable<T>.mapTo(destination: C, transform: (T) -> R): C {
        for (item in this)
          destination.add(transform(item))
            return destination
         ->
        * public inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R> {
            return mapTo(ArrayList<R>(collectionSizeOrDefault(10)), transform)
        }
        */
        //list<UserEntity>요소가 다 끝날때 까지 User(UserEntity요소) 추가
        return map { User(it.id, it.login, it.avatar_url, it.checked) }
    }
}

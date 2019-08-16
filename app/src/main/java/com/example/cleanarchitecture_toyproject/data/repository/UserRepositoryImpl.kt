package com.example.cleanarchitecture_toyproject.data.repository

import android.util.Log
import com.example.cleanarchitecture_toyproject.data.mapper.UserEntityMapper
import com.example.cleanarchitecture_toyproject.data.user.UserCacheDataSource
import com.example.cleanarchitecture_toyproject.domain.repository.UserRepository
import com.example.cleanarchitecture_toyproject.domain.User
import io.reactivex.Observable

//해당 기능에 관련된 repository / repository는 하나의 데이터만 컨트롤 하게/ 분리되는건 다 분리시키기
//UserRepository, FavoriteUserRepository

//Cache -> (interface)UserEntityCache, favoriteUserCache, -> UserEntityCacheImpl, favoriteUserCacheImpl
//TODO lateinit, lazy() 차이점
// TODO sam , 람다, 스트
class UserRepositoryImpl(
    private val userCacheDataSource: UserCacheDataSource,
    private val userEntityMapper: UserEntityMapper,
    private val userModelMapper: UserEntityMapper
) : UserRepository {

    //datasource로 부터 의존성 주입 받기
    //db에서 favorite user정보를 가져온다.
    /***
     *
     * return userCacheDataSource.selectUsers().map(new Function<List></List><UserEntity>, List<User>>() {
     * @Override
     * public List<User> apply(List<UserEntity> userModels) throws Exception {
     * return userModelMapper.transform(userModels);
     * }
     * });
     *
    </UserEntity></User></User></UserEntity> */
    override fun selectUsers(): Observable<List<User>> {
        /*userCacheDataSource.selectUsers().map { userModels -> userModelMapper.transform(userModels) }*/
        //위의 코드를 람다로 바꾼 것
        return userCacheDataSource.selectUsers().map(userModelMapper::transform)
    }

    override//usermodel-> userentity
    fun setUsers(user: User) {
        Log.d("debug123", user.checked.toString())
        userCacheDataSource.setUsers(userEntityMapper.transform(user))
    }

    override fun deleteUsers(user: User) {
        userCacheDataSource.deleteUsers(userEntityMapper.transform(user))

    }

}

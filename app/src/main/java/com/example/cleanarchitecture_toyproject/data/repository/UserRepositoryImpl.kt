package com.example.cleanarchitecture_toyproject.data.repository

import android.util.Log
import com.example.cleanarchitecture_toyproject.data.mapper.UserEntityMapper
import com.example.cleanarchitecture_toyproject.data.mapper.UserModelMapper
import com.example.cleanarchitecture_toyproject.data.user.UserDataSource
import com.example.cleanarchitecture_toyproject.data.user.UserDataSourceImpl
import com.example.cleanarchitecture_toyproject.domain.repository.UserRepository
import com.example.cleanarchitecture_toyproject.domain.User
import io.reactivex.Observable

//해당 기능에 관련된 repository / repository는 하나의 데이터만 컨트롤 하게/ 분리되는건 다 분리시키기
//UserRepository, FavoriteUserRepository

//Cache -> (interface)UserCache, favoriteUserCache, -> UserCacheImpl, favoriteUserCacheImpl
class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
    private val userEntityMapper: UserEntityMapper,
    private val userModelMapper: UserModelMapper
) : UserRepository {

    //datasource로 부터 의존성 주입 받기
    override fun getUsers(userName: String): Observable<List<User>> {
        Log.d("UserRepositoryImpl", "getUsers")
        /*return userDataSource.getUsers(userName)
            .map(Function<List<UserEntity>, List<User>> { this.userEntityMapper.transform(it) })*/
        return userDataSource.getUsers(userName).map {userEntityCollections-> userEntityMapper.transform(userEntityCollections)}
    }

    //db에서 favorite user정보를 가져온다.
    /***
     *
     * return userDataSource.selectUsers().map(new Function<List></List><UserEntity>, List<User>>() {
     * @Override
     * public List<User> apply(List<UserEntity> userModels) throws Exception {
     * return userModelMapper.transform(userModels);
     * }
     * });
     *
    </UserEntity></User></User></UserEntity> */
    override fun selectUsers(): Observable<List<User>> {
        return userDataSource.selectUsers().map { userModels -> userModelMapper.transform(userModels) }
    }

    override//usermodel-> userentity
    fun setUsers(user: User) {
        Log.d("debug123", user.checked.toString())
        userDataSource.setUsers(userEntityMapper.transform(user))
    }

    override fun deleteUsers(user: User) {
        userDataSource.deleteUsers(userEntityMapper.transform(user))

    }

    companion object {

        private var instance: UserRepositoryImpl? = null

        //userRepository -> userdatasource
        fun getInstance(): UserRepositoryImpl? {
            if (instance == null) {
                synchronized(UserRepositoryImpl::class.java) {
                    if (instance == null) {
                        instance =
                            UserRepositoryImpl(UserDataSourceImpl.instance!!, UserEntityMapper(), UserModelMapper())
                    }
                }
            }
            return instance
        }
    }
}

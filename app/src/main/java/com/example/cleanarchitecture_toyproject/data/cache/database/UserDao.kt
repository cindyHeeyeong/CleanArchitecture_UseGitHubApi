package com.example.cleanarchitecture_toyproject.data.cache.database

import androidx.room.*
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity
import io.reactivex.Observable

@Dao
interface UserDao {

    @Insert
    fun insert(userEntity: UserEntity)

    //모든 user 정보 불러오기
    @Query("SELECT * FROM user")
    fun loadAllUser(): Observable<List<UserEntity>>

    //전달받은 id값으로 데이터 select
    @Query("SELECT * FROM user WHERE id = :id")
    fun loadIDUser(id: Int): UserEntity

    //데이터 삭제
    @Delete
    fun deleteUser(userEntity: UserEntity)

}

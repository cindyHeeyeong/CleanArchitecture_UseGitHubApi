package com.example.cleanarchitecture_toyproject.data.cache.database;

import androidx.annotation.NonNull;
import androidx.room.*;
import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;
import io.reactivex.Observable;
import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(UserEntity userEntity);

    //모든 user 정보 불러오기
    @Query("SELECT * FROM user")
    Observable<List<UserEntity>> loadAllUser();

    //전달받은 id값으로 데이터 select
    @Query("SELECT * FROM user WHERE id = :id")
    UserEntity loadIDUser(@NonNull int id);

    //데이터 삭제
    @Delete
    void deleteUser(UserEntity userEntity);

}

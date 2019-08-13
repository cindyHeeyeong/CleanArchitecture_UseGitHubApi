package com.example.cleanarchitecture_toyproject.domain.repository

import com.example.cleanarchitecture_toyproject.domain.User
import io.reactivex.Observable

//TODO 하나의 데이터만 컨트롤 하게할지, 한개의 데이터에서 데이터를 다 처리할지 구조 짜는 것 생각해보기
//git test
interface UserRepository {
    //Repository는 하나의 데이터만 처리해야 한다.
    //예를 들어 userEntity에 대한 data만 들어가야 한다.
    //token을 꺼내오는 data가 들어가면 안된다.

    //Observable<List<User>> getUsers();
    fun getUsers(userName: String): Observable<List<User>>

    //room에서 select한 데이터 가져오기
    fun selectUsers(): Observable<List<User>>

    //insert userModel
    fun setUsers(user: User)

    //delete userModel
    fun deleteUsers(user: User)
}

package com.example.cleanarchitecture_toyproject.domain.usecase

import com.example.cleanarchitecture_toyproject.domain.User
import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread
import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor
import com.example.cleanarchitecture_toyproject.domain.repository.UserRemoteRepository
import io.reactivex.Observable

//db목록 가져오기
//GetUserListUsecase
//GetUsers, GetUserList로 할건지 정하기
class GetUserListUseCase(
    val userRemoteRepository: UserRemoteRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<List<User>, GetUserListUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Observable<List<User>> {
        return this.userRemoteRepository.getUsers(params?.userName!!)
    }

    class Params private constructor(var userName: String) {
        companion object {

            fun forUser(userName: String): Params {
                return Params(userName)
            }
        }
    }
}

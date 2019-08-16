package com.example.cleanarchitecture_toyproject.domain.usecase

import com.example.cleanarchitecture_toyproject.domain.User
import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread
import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor
import com.example.cleanarchitecture_toyproject.domain.repository.UserRepository
import io.reactivex.Observable

//db 정보를 가져오는 usecase
class SelectUserListUseCase(
    private val userRepository: UserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : UseCase<List<User>, Void>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(aVoid: Void?): Observable<List<User>> {
        return this.userRepository!!.selectUsers()
    }
}

package com.example.cleanarchitecture_toyproject.domain.usecase

import com.example.cleanarchitecture_toyproject.domain.User
import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread
import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor
import com.example.cleanarchitecture_toyproject.domain.repository.UserRepository
import io.reactivex.Completable

//즐겨찾기를 누른 유저 정보 리스트

class SetUserListUseCase
    (
    private val userRepository: UserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<SetUserListUseCase.Request>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(request: Request): Completable {
        return Completable.fromAction { userRepository!!.setUsers(request.task) }
    }

    //param으로 바꾸기
    class Request(val task: User)


}

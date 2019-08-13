package com.example.cleanarchitecture_toyproject.domain.usecase

import com.example.cleanarchitecture_toyproject.domain.User
import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread
import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor
import com.example.cleanarchitecture_toyproject.domain.repository.UserRepository
import io.reactivex.Completable

class DeleteUserListUseCase(
    private val userRepository: UserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<DeleteUserListUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(params: Params): Completable {
        return Completable.fromAction { userRepository.deleteUsers(params.user) }
    }

    class Params(val user: User)
}

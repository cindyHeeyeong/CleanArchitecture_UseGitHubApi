package com.example.cleanarchitecture_toyproject.domain.usecase;

import com.example.cleanarchitecture_toyproject.domain.User;
import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread;
import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor;
import com.example.cleanarchitecture_toyproject.domain.repository.UserRepository;
import io.reactivex.Completable;

public class DeleteUserListUseCase extends CompletableUseCase<DeleteUserListUseCase.Params> {

    private final UserRepository userRepository;

    public DeleteUserListUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Completable buildUseCase(Params params) {
        return Completable.fromAction(() -> userRepository.deleteUsers(params.getUser()));
    }

    public static final class Params {

        private final User user;

        public Params(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }
}

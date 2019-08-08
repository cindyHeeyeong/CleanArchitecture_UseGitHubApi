package com.example.cleanarchitecture_toyproject.domain.usecase;

import com.example.cleanarchitecture_toyproject.domain.User;
import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread;
import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor;
import com.example.cleanarchitecture_toyproject.domain.repository.UserRepository;
import io.reactivex.Observable;

import java.util.List;

//db목록 가져오기
//GetUserListUsecase
//GetUsers, GetUserList로 할건지 정하기
public class GetUserListUseCase extends UseCase<List<User>, GetUserListUseCase.Params> {

    private final UserRepository userRepository;

    public GetUserListUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    Observable<List<User>> buildUseCaseObservable(Params params) {
        return this.userRepository.getUsers(params.userName);
    }

    public static final class Params {

        private final String userName;

        private Params(String userName) {
            this.userName = userName;
        }

        public static Params forUser(String userName) {
            return new Params(userName);
        }
    }
}

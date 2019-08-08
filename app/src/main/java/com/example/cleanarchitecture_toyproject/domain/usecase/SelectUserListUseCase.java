package com.example.cleanarchitecture_toyproject.domain.usecase;

import com.example.cleanarchitecture_toyproject.domain.User;
import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread;
import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor;
import com.example.cleanarchitecture_toyproject.domain.repository.UserRepository;
import io.reactivex.Observable;

import java.util.List;

//db 정보를 가져오는 usecase
public class SelectUserListUseCase extends UseCase<List<User>, Void>{

    private final UserRepository userRepository;

    public SelectUserListUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    Observable<List<User>> buildUseCaseObservable (Void aVoid){
        return this.userRepository.selectUsers();
    }
}

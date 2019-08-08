package com.example.cleanarchitecture_toyproject.domain.usecase;


import com.example.cleanarchitecture_toyproject.domain.User;
import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread;
import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor;
import com.example.cleanarchitecture_toyproject.domain.repository.UserRepository;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
import io.reactivex.Completable;

//즐겨찾기를 누른 유저 정보 리스트

public class SetUserListUseCase extends CompletableUseCase<SetUserListUseCase.Request> {

    private final UserRepository userRepository;

    //생성자에 스레드를 주입했기 때문에 usecase에서 계속 스레드가 돌고 있기 때문에 다른 곳에서 쓰레드를 돌릴 필요 없다.
    public SetUserListUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Completable buildUseCase(Request request) {
        return Completable.fromAction(() -> userRepository.setUsers(request.getTask()));
    }

    public static final class Request {

        private final User userModel;

        public Request(User userModel) {
            this.userModel = userModel;
        }

        public User getTask() {
            return userModel;
        }
    }


}

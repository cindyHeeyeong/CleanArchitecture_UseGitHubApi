package com.example.cleanarchitecture_toyproject.domain.usecase;

        import com.example.cleanarchitecture_toyproject.data.entity.UserEntity;
        import com.example.cleanarchitecture_toyproject.domain.User;
        import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread;
        import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor;
        import com.example.cleanarchitecture_toyproject.domain.repository.UserRepository;
        import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
        import io.reactivex.Completable;

public class DeleteUserListUseCase extends CompletableUseCase<DeleteUserListUseCase.Request>{

    private final UserRepository userRepository;

    public DeleteUserListUseCase(UserRepository userRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        super(threadExecutor,postExecutionThread);
        this.userRepository = userRepository;
    }

    @Override
    protected Completable buildUseCase(Request request) {
        return Completable.fromAction(() -> userRepository.deleteUsers(request.getUser()));
    }

    public static final class Request {

        private final User userModel;

        public Request(User userModel) {
            this.userModel = userModel;
        }

        public User getUser() {
            return userModel;
        }
    }
}

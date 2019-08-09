package com.example.cleanarchitecture_toyproject.viewmodel.presenter;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.cleanarchitecture_toyproject.domain.User;
import com.example.cleanarchitecture_toyproject.domain.usecase.DefaultObserver;
import com.example.cleanarchitecture_toyproject.domain.usecase.DeleteUserListUseCase;
import com.example.cleanarchitecture_toyproject.domain.usecase.GetUserListUseCase;
import com.example.cleanarchitecture_toyproject.domain.usecase.SetUserListUseCase;
import com.example.cleanarchitecture_toyproject.viewmodel.mapper.UserModelMapper;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserListView;
import java.util.List;

public class UserListPresenter{

    private UserListView viewListView;

    private GetUserListUseCase getUserListUseCase;
    private SetUserListUseCase setUserListUserCaseUseCase;
    private DeleteUserListUseCase deleteUserListUseCase;

    private UserModelMapper userModelMapper;

    public UserListPresenter(GetUserListUseCase getUserListUseCase, UserModelMapper userModelMapper) {
        this.getUserListUseCase = getUserListUseCase;
        this.userModelMapper = userModelMapper;
    }

    public UserListPresenter(SetUserListUseCase setUserListUserCaseUseCase, UserModelMapper userModelMapper) {
        this.setUserListUserCaseUseCase = setUserListUserCaseUseCase;
        this.userModelMapper = userModelMapper;
    }

    public UserListPresenter(DeleteUserListUseCase deleteUserListUseCase, UserModelMapper userModelMapper) {
        this.deleteUserListUseCase = deleteUserListUseCase;
        this.userModelMapper = userModelMapper;
    }

    public void setView(@NonNull UserListView view){
        this.viewListView = view;
    }

    //usecase execute
    public void getUserList(String userName) {

        this.getUserListUseCase.execute(new DefaultObserver<List<User>>(){
            @Override
            public void onNext(List<User> users) {
                Log.d("favoriteUserpresenter","onNext");
                UserListPresenter.this.showUserCollectionInView(users);
            }

            @Override
            public void onComplete() {
                Log.d("favoriteUserpresenter", "usecase execute success");
            }

            @Override
            public void onError(Throwable exception) {
                Log.d("favoriteUserpresenter","error"+exception);
            }
        }, GetUserListUseCase.Params.forUser(userName));

    }

    //db에 유저 정보 저장
    public void insertUserList(UserModel userModel){
        userModel = new UserModel(userModel.getId(), userModel.getLogin(), userModel.getAvatar_url(),userModel.getChecked());
        SetUserListUseCase.Request request = new SetUserListUseCase.Request(userModelMapper.transform(userModel));

        this.setUserListUserCaseUseCase.subscribe(request);
    }

    public void deleteUser(UserModel userModel) {


        userModel = new UserModel(userModel.getId(), userModel.getLogin(), userModel.getAvatar_url(),userModel.getChecked());
        DeleteUserListUseCase.Params params = new DeleteUserListUseCase.Params(userModelMapper.transform(userModel));

        this.deleteUserListUseCase.subscribe(params);
    }


    //data mapping
    private void showUserCollectionInView(List<User> usersCollection) {
        List<UserModel> userModelCollection = this.userModelMapper.transform(usersCollection);
        this.viewListView.renderUserlist(userModelCollection);
    }
}

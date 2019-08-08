package com.example.cleanarchitecture_toyproject.viewmodel.presenter;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.cleanarchitecture_toyproject.domain.User;
import com.example.cleanarchitecture_toyproject.domain.usecase.DefaultObserver;
import com.example.cleanarchitecture_toyproject.domain.usecase.DeleteUserListUseCase;
import com.example.cleanarchitecture_toyproject.domain.usecase.SelectUserListUseCase;
import com.example.cleanarchitecture_toyproject.viewmodel.mapper.UserModelMapper;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserListView;
import java.util.List;

public class FavoriteUserPresenter{

    private UserListView viewListView;

    private SelectUserListUseCase selectUserListUsecase;

    private DeleteUserListUseCase deleteUserListUseCase;

    private UserModelMapper userModelMapper;

    public FavoriteUserPresenter(SelectUserListUseCase selectUserListUseCase, UserModelMapper userModelMapper) {
        this.selectUserListUsecase = selectUserListUseCase;
        this.userModelMapper = userModelMapper;
    }

    public FavoriteUserPresenter(DeleteUserListUseCase deleteUserListUseCase, UserModelMapper userModelMapper){
        this.deleteUserListUseCase = deleteUserListUseCase;
        this.userModelMapper = userModelMapper;
    }

    public void setView(@NonNull UserListView view){
        this.viewListView = view;
    }


    //observer 지우기, 이 형식으로 만들기
    public void SelectFavoriteUser(){
        this.selectUserListUsecase.execute(new DefaultObserver<List<User>>(){
            @Override
            public void onNext(List<User> users) {
                Log.d("favoriteUserpresenter","onNext");
                FavoriteUserPresenter.this.showUserCollectionInView(users);
            }
            @Override
            public void onComplete() {
                Log.d("UserListPresenter", "usecase execute success");
            }

            @Override
            public void onError(Throwable exception) {
                Log.d("UserListPresenter error","error"+exception);
            }
        }, null);
    }


    //userModel 정보 삭제
    public void deleteUserData(UserModel userModel) {

        DeleteUserListUseCase.Request request = new DeleteUserListUseCase.Request(userModelMapper.transform(userModel));

        this.deleteUserListUseCase.subscribe(request);
    }


    //mapping
    public void showUserCollectionInView(List<User> userCollection) {

        List<UserModel> userModelCollection = userModelMapper.transform(userCollection);
        this.viewListView.renderUserlist(userModelCollection);

    }
}

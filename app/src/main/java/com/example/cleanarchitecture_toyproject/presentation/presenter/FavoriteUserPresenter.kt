package com.example.cleanarchitecture_toyproject.presentation.presenter

import android.util.Log
import com.example.cleanarchitecture_toyproject.domain.User
import com.example.cleanarchitecture_toyproject.domain.usecase.DefaultObserver
import com.example.cleanarchitecture_toyproject.domain.usecase.DeleteUserListUseCase
import com.example.cleanarchitecture_toyproject.domain.usecase.SelectUserListUseCase
import com.example.cleanarchitecture_toyproject.presentation.mapper.UserModelMapper
import com.example.cleanarchitecture_toyproject.presentation.model.UserModel
import com.example.cleanarchitecture_toyproject.presentation.ui.UserListView

class FavoriteUserPresenter(
    val selectUserListUsecase: SelectUserListUseCase,
    val deleteUserListUseCase: DeleteUserListUseCase,
    val userModelMapper: UserModelMapper
) {

    private var viewListView: UserListView? = null

    fun setView(view: UserListView) {
        this.viewListView = view
    }


    //observer 지우기, 이 형식으로 만들기
    fun SelectFavoriteUser() {
        this.selectUserListUsecase.execute(object : DefaultObserver<List<User>>() {
            override fun onNext(users: List<User>) {
                Log.d("favoriteUserpresenter", "onNext")
                this@FavoriteUserPresenter.showUserCollectionInView(users)
            }
        }, null)
    }


    //userModel 정보 삭제
    fun deleteUserData(userModel: UserModel) {
        val params = DeleteUserListUseCase.Params(userModelMapper.transform(userModel))
        this.deleteUserListUseCase.subscribe(params)
    }


    //mapping
    fun showUserCollectionInView(userCollection: List<User>) {
        val userModelCollection = userModelMapper.transform(userCollection)
        this.viewListView!!.renderUserlist(userModelCollection)

    }
}

package com.example.cleanarchitecture_toyproject.viewmodel.presenter

import android.util.Log
import com.example.cleanarchitecture_toyproject.domain.User
import com.example.cleanarchitecture_toyproject.domain.usecase.DefaultObserver
import com.example.cleanarchitecture_toyproject.domain.usecase.DeleteUserListUseCase
import com.example.cleanarchitecture_toyproject.domain.usecase.SelectUserListUseCase
import com.example.cleanarchitecture_toyproject.viewmodel.mapper.UserModelMapper
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserListView

class FavoriteUserPresenter {

    private var viewListView: UserListView? = null

    private lateinit var selectUserListUsecase: SelectUserListUseCase

    private lateinit var deleteUserListUseCase: DeleteUserListUseCase

    private var userModelMapper: UserModelMapper? = null

    constructor(selectUserListUseCase: SelectUserListUseCase, userModelMapper: UserModelMapper) {
        this.selectUserListUsecase = selectUserListUseCase
        this.userModelMapper = userModelMapper
    }

    constructor(deleteUserListUseCase: DeleteUserListUseCase, userModelMapper: UserModelMapper) {
        this.deleteUserListUseCase = deleteUserListUseCase
        this.userModelMapper = userModelMapper
    }

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

            override fun onComplete() {
                Log.d("UserListPresenter", "usecase execute success")
            }

            override fun onError(exception: Throwable) {
                Log.d("UserListPresenter error", "error$exception")
            }
        }, null)
    }


    //userModel 정보 삭제
    fun deleteUserData(userModel: UserModel) {

        val params = DeleteUserListUseCase.Params(userModelMapper!!.transform(userModel))

        this.deleteUserListUseCase.subscribe(params)
    }


    //mapping
    fun showUserCollectionInView(userCollection: List<User>) {
        val userModelCollection = userModelMapper!!.transform(userCollection)
        this.viewListView!!.renderUserlist(userModelCollection)

    }
}

package com.example.cleanarchitecture_toyproject.viewmodel.presenter

import android.util.Log
import com.example.cleanarchitecture_toyproject.domain.User
import com.example.cleanarchitecture_toyproject.domain.usecase.DefaultObserver
import com.example.cleanarchitecture_toyproject.domain.usecase.DeleteUserListUseCase
import com.example.cleanarchitecture_toyproject.domain.usecase.GetUserListUseCase
import com.example.cleanarchitecture_toyproject.domain.usecase.SetUserListUseCase
import com.example.cleanarchitecture_toyproject.viewmodel.mapper.UserModelMapper
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserListView

class UserListPresenter {

    private var viewListView: UserListView? = null

    private lateinit var getUserListUseCase: GetUserListUseCase
    private lateinit var setUserListUserCaseUseCase: SetUserListUseCase
    private lateinit var deleteUserListUseCase: DeleteUserListUseCase

    private var userModelMapper: UserModelMapper? = null

    constructor(getUserListUseCase: GetUserListUseCase, userModelMapper: UserModelMapper) {
        this.getUserListUseCase = getUserListUseCase
        this.userModelMapper = userModelMapper
    }

    constructor(setUserListUserCaseUseCase: SetUserListUseCase, userModelMapper: UserModelMapper) {
        this.setUserListUserCaseUseCase = setUserListUserCaseUseCase
        this.userModelMapper = userModelMapper
    }

    constructor(deleteUserListUseCase: DeleteUserListUseCase, userModelMapper: UserModelMapper) {
        this.deleteUserListUseCase = deleteUserListUseCase
        this.userModelMapper = userModelMapper
    }

    fun setView(view: UserListView) {
        this.viewListView = view
    }

    //usecase execute
    fun getUserList(userName: String) {

        this.getUserListUseCase.execute(object : DefaultObserver<List<User>>() {
            override fun onNext(users: List<User>) {
                Log.d("favoriteUserpresenter", "onNext")
                this@UserListPresenter.showUserCollectionInView(users)
            }

            override fun onComplete() {
                Log.d("favoriteUserpresenter", "usecase execute success")
            }

            override fun onError(exception: Throwable) {
                Log.d("favoriteUserpresenter", "error$exception")
            }
        }, GetUserListUseCase.Params.forUser(userName))

    }

    //db에 유저 정보 저장
    fun insertUserList(userModel: UserModel) {
        var userModel = userModel
        userModel = UserModel(userModel.id, userModel.login, userModel.avatar_url, userModel.checked)
        val request = SetUserListUseCase.Request(userModelMapper!!.transform(userModel))

        this.setUserListUserCaseUseCase.subscribe(request)
    }

    fun deleteUser(userModel: UserModel) {
        var userModel = userModel


        userModel = UserModel(userModel.id, userModel.login, userModel.avatar_url, userModel.checked)
        val params = DeleteUserListUseCase.Params(userModelMapper!!.transform(userModel))

        this.deleteUserListUseCase.subscribe(params)
    }


    //data mapping
    private fun showUserCollectionInView(usersCollection: List<User>) {
        val userModelCollection = this.userModelMapper!!.transform(usersCollection)
        this.viewListView!!.renderUserlist(userModelCollection)
    }
}

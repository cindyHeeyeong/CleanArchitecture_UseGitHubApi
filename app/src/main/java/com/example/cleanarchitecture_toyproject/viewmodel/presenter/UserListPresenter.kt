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

class UserListPresenter(val getUserListUseCase: GetUserListUseCase,
                        val setUserListUserCaseUseCase: SetUserListUseCase,
                        val deleteUserListUseCase: DeleteUserListUseCase,
                        val userModelMapper: UserModelMapper) {

    private var viewListView: UserListView? = null

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

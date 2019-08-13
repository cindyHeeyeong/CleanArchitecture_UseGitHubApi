package com.example.cleanarchitecture_toyproject.viewmodel.view.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture_toyproject.R
import com.example.cleanarchitecture_toyproject.data.executor.JobExecutor
import com.example.cleanarchitecture_toyproject.data.repository.UserRepositoryImpl
import com.example.cleanarchitecture_toyproject.databinding.FragmentFavoriteUsersBinding
import com.example.cleanarchitecture_toyproject.domain.executor.UIThread
import com.example.cleanarchitecture_toyproject.domain.usecase.DeleteUserListUseCase
import com.example.cleanarchitecture_toyproject.domain.usecase.SelectUserListUseCase
import com.example.cleanarchitecture_toyproject.viewmodel.RxBus.RxEventBus
import com.example.cleanarchitecture_toyproject.viewmodel.mapper.UserModelMapper
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel
import com.example.cleanarchitecture_toyproject.viewmodel.presenter.FavoriteUserPresenter
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserClickListener
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserListView
import com.example.cleanarchitecture_toyproject.viewmodel.view.adapter.UsersAdapter
import java.util.ArrayList

//즐겨찾기 유저를 보여주는 화면
class UserFavoriteFragment : Fragment(), UserListView {

    private lateinit var favoriteUserPresenter: FavoriteUserPresenter

    private lateinit var deleteUserPresenter: FavoriteUserPresenter

    private lateinit var binding: FragmentFavoriteUsersBinding

    private val usersList = ArrayList<UserModel>()

    private lateinit var usersAdapter: UsersAdapter

    private val userModels = ArrayList<UserModel>()
    //onCreateView에서는
    // 한번 이상 실행될 수 있다.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_users, container, false)

        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        favoriteUserPresenter = FavoriteUserPresenter(
            SelectUserListUseCase(UserRepositoryImpl.getInstance()!!, JobExecutor(), UIThread()),
            UserModelMapper()
        )

        deleteUserPresenter = FavoriteUserPresenter(
            DeleteUserListUseCase(UserRepositoryImpl.getInstance()!!, JobExecutor(), UIThread()),
            UserModelMapper()
        )

        favoriteUserPresenter!!.setView(this)

        //TODO

        RxEventBus.instance!!.bus.map { `object` -> `object` as UserModel }
            .subscribe { userModel ->
                if (userModel is UserModel) {

                    Log.d("rxeventbus2222", userModel.avatar_url.toString())

                    userModels.add(userModel)

                    setupRecyclerView()
                }
            }


        //TODO 정상 작동 코드(아이템 클릭했을 때 해당 아이템이 삭제되게 만들려면 이 코드로 하기
        //favoriteUserPresenter.SelectFavoriteUser();

        //리사이클러뷰 셋팅
        //setupRecyclerView();
    }


    private fun setupRecyclerView() {
        Log.d("userModels", userModels.toString())
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), LinearLayoutManager(requireActivity()).orientation)
        //recyclerview setting
        val recyclerView = binding!!.rvUsers
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.setHasFixedSize(true)
        //usersAdapter = new UsersAdapter(usersList);
        usersAdapter = UsersAdapter(userModels)
        usersAdapter!!.setOnItemClickListener(object : UserClickListener {
            override fun setOnClick(userModel: UserModel) {
                Log.v("DEBUG999", "userModel :$userModel")
                usersAdapter!!.notifyDataSetChanged()

                //delete user data
                deleteUserPresenter!!.deleteUserData(userModel)
            }
        })
        recyclerView.adapter = usersAdapter
    }

    override fun renderUserlist(userModelCollection: List<UserModel>) {
        if (userModelCollection != null) {
            usersAdapter!!.setUserCollection(userModelCollection)
        }
        usersAdapter!!.notifyDataSetChanged()
    }

    override fun context(): Context {
        return context()
    }
}

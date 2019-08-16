package com.example.cleanarchitecture_toyproject.presentation.ui.Fragment

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
import com.example.cleanarchitecture_toyproject.R
import com.example.cleanarchitecture_toyproject.databinding.FragmentFavoriteUsersBinding
import com.example.cleanarchitecture_toyproject.presentation.RxBus.RxEventBus
import com.example.cleanarchitecture_toyproject.presentation.model.UserModel
import com.example.cleanarchitecture_toyproject.presentation.presenter.FavoriteUserPresenter
import com.example.cleanarchitecture_toyproject.presentation.ui.UserClickListener
import com.example.cleanarchitecture_toyproject.presentation.ui.UserListView
import com.example.cleanarchitecture_toyproject.presentation.ui.adapter.UsersAdapter
import org.koin.android.ext.android.inject
import java.util.ArrayList

//즐겨찾기 유저를 보여주는 화면
class UserFavoriteFragment : Fragment(), UserListView {


    private lateinit var binding: FragmentFavoriteUsersBinding

    private lateinit var usersAdapter: UsersAdapter

    private val userModels = ArrayList<UserModel>()

    private val favoriteUserPresenter : FavoriteUserPresenter by inject()


    //onCreateView에서는
    // 한번 이상 실행될 수 있다.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_users, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        favoriteUserPresenter!!.setView(this)

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
        usersAdapter = UsersAdapter(userModels)
        usersAdapter!!.setOnItemClickListener(object : UserClickListener {
            override fun setOnClick(userModel: UserModel) {
                Log.v("DEBUG999", "userModel :$userModel")
                usersAdapter!!.notifyDataSetChanged()

                //delete user data
                favoriteUserPresenter.deleteUserData(userModel)
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

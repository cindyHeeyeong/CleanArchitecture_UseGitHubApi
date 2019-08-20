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
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture_toyproject.R
import com.example.cleanarchitecture_toyproject.databinding.FragmentUserSearchBinding
import com.example.cleanarchitecture_toyproject.presentation.RxBus.RxEventBus
import com.example.cleanarchitecture_toyproject.presentation.model.UserModel
import com.example.cleanarchitecture_toyproject.presentation.presenter.UserListPresenter
import com.example.cleanarchitecture_toyproject.presentation.ui.UserClickListener
import com.example.cleanarchitecture_toyproject.presentation.ui.UserListView
import com.example.cleanarchitecture_toyproject.presentation.ui.adapter.UsersAdapter
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import java.util.ArrayList
import java.util.concurrent.TimeUnit

//user를 검색하는 fragment이다
class UserSearchFragment : Fragment(), UserListView {

    private lateinit var binding: FragmentUserSearchBinding

    //editText에서 입력 받은 사용자 이름
    private var userName = ""

    //recyclerview
    private lateinit var recyclerView: RecyclerView
    private lateinit var usersAdapter: UsersAdapter

    private val usersList = ArrayList<UserModel>()

    //inject
    private val userListPresenter : UserListPresenter by inject()
    private val rxEventBus : RxEventBus by inject()


    private val disposable = CompositeDisposable()

    init {
        retainInstance = true
    }

    //onActivityCreated에서 databinding 생성하기
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //fragment setting
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_search, container, false)
        Log.d("debug789","presenter -> $userListPresenter")
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userListPresenter.setView(this)

        //리사이클러뷰 셋팅
        setupRecyclerView()

        disposable.add(
            RxTextView.textChangeEvents(binding.userSearchEdt)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchQuery())
        )


        //버튼을 눌렀을 때
        binding.userSearchBtn.setOnClickListener { view ->
            userName = binding.userSearchEdt.text.toString()
            loadUserList(userName)
        }
    }

    private fun searchQuery(): DisposableObserver<TextViewTextChangeEvent> {
        return object : DisposableObserver<TextViewTextChangeEvent>() {
            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                Log.d("search string: ", textViewTextChangeEvent.text().toString())
                userName=textViewTextChangeEvent.text().toString()
                loadUserList(textViewTextChangeEvent.text().toString())

            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        }
    }

    fun loadTest() {
        Log.d("debug788","presenter -> $userName")
        this.userListPresenter.getUserList(userName)
    }

    //userlist 정보 끌어오기
    fun loadUserList(userName: String) {
        Log.d("loadUserList", userName)
        this.userListPresenter.getUserList(userName)
    }

    //리사이클러 뷰 셋팅
    fun setupRecyclerView() {
        Log.d("setupRecyclerView", "start")
        val dividerItemDecoration =
            DividerItemDecoration(requireActivity(), LinearLayoutManager(requireActivity()).orientation)
        recyclerView = binding.rvUsers
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.setHasFixedSize(true)
        usersAdapter = UsersAdapter(usersList)
        usersAdapter.setOnItemClickListener(object : UserClickListener {
            override fun setOnClick(userModel: UserModel) {

                usersAdapter.notifyDataSetChanged()
                //원 코드
                if (userModel.checked) {
                    Log.d("getChecked", "true")
                    userListPresenter.insertUserList(userModel)
                    rxEventBus.sendBus(userModel)
                } else {
                    Log.d("getChecked", "false")
                    userListPresenter.deleteUser(userModel)
                }
            }
        })
        recyclerView.adapter = usersAdapter
    }

    override fun renderUserlist(userModelCollection: List<UserModel>) {
        Log.v("DEBUG900", "renderUserList")
        this.usersAdapter.setUserCollection(userModelCollection)
        usersAdapter.notifyDataSetChanged()
    }

    override fun context(): Context {
        return context()
    }
}

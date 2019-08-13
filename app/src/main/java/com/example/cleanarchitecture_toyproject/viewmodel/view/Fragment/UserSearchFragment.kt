package com.example.cleanarchitecture_toyproject.viewmodel.view.Fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture_toyproject.R
import com.example.cleanarchitecture_toyproject.data.executor.JobExecutor
import com.example.cleanarchitecture_toyproject.data.repository.UserRepositoryImpl
import com.example.cleanarchitecture_toyproject.databinding.FragmentUserSearchBinding
import com.example.cleanarchitecture_toyproject.domain.executor.UIThread
import com.example.cleanarchitecture_toyproject.domain.usecase.DeleteUserListUseCase
import com.example.cleanarchitecture_toyproject.domain.usecase.GetUserListUseCase
import com.example.cleanarchitecture_toyproject.domain.usecase.SetUserListUseCase
import com.example.cleanarchitecture_toyproject.provider.DatabaseProvider
import com.example.cleanarchitecture_toyproject.viewmodel.RxBus.RxEventBus
import com.example.cleanarchitecture_toyproject.viewmodel.mapper.UserModelMapper
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel
import com.example.cleanarchitecture_toyproject.viewmodel.presenter.UserListPresenter
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserClickListener
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserListView
import com.example.cleanarchitecture_toyproject.viewmodel.view.adapter.UsersAdapter
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

import java.util.ArrayList
import java.util.concurrent.TimeUnit

//user를 검색하는 fragment이다
class UserSearchFragment : Fragment(), UserListView {

    private var binding: FragmentUserSearchBinding? = null

    //사용자 이름 검색 후 list를 불러오는 presenter
    private var userListPresenter: UserListPresenter? = null

    //즐겨찾기 유저 저장하기 presenter
    private var setUserListPresenter: UserListPresenter? = null

    //즐겨찾기 유저 삭제 presenter
    private var deleteUserListPresenter: UserListPresenter? = null
    private val TextWatcher: Any? = null

    //editText에서 입력 받은 사용자 이름
    private var userName = ""

    //recyclerview
    private var recyclerView: RecyclerView? = null
    private var usersAdapter: UsersAdapter? = null

    private val usersList = ArrayList<UserModel>()

    //provider
    lateinit var databaseProvider: DatabaseProvider

    private val str = arrayOf("0", "1", "10", "100", "1000")

    private val disposable = CompositeDisposable()

    init {
        retainInstance = true
    }

    //onActivityCreated에서 databinding 생성하기
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //fragment setting
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_search, container, false)

        Log.d("onCreateView", "onCreateView")
        return binding!!.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("onActivityCreated", "onActivityCreated")
        userListPresenter = UserListPresenter(
            GetUserListUseCase(UserRepositoryImpl.getInstance()!!, JobExecutor(), UIThread()),
            UserModelMapper()
        )
        setUserListPresenter = UserListPresenter(
            SetUserListUseCase(UserRepositoryImpl.getInstance()!!, JobExecutor(), UIThread()),
            UserModelMapper()
        )
        deleteUserListPresenter = UserListPresenter(
            DeleteUserListUseCase(UserRepositoryImpl.getInstance()!!, JobExecutor(), UIThread()),
            UserModelMapper()
        )
        userListPresenter!!.setView(this)

        //getActivity --> requireActivity()
        databaseProvider = DatabaseProvider(requireActivity())

        //리사이클러뷰 셋팅
        setupRecyclerView()

        disposable.add(
            RxTextView.textChangeEvents(binding!!.userSearchEdt)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchQuery())
        )


        /*binding!!.userSearchBtn.setOnClickListener(object : Button.OnClickListener {
            override fun onClick(view: View) {
                userName = binding!!.userSearchEdt.text!!.toString()
                Log.d("userName", userName)
                loadUserList(userName)
            }
        })*/


        binding?.userSearchBtn?.setOnClickListener { view ->
            userName = binding?.userSearchEdt?.text.toString()
            loadUserList(userName)
        }
    }

    private fun searchQuery(): DisposableObserver<TextViewTextChangeEvent> {
        Log.d("searchQuery", "searchQuery")
        return object : DisposableObserver<TextViewTextChangeEvent>() {
            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
                Log.d("search string: ", textViewTextChangeEvent.text().toString())
                loadUserList(textViewTextChangeEvent.text().toString())

            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        }
    }

    fun favoriteDataSync() {
        Log.d("fragementStart", "start")
        loadUserList(userName)
    }

    //userlist 정보 끌어오기
    fun loadUserList(userName: String) {
        Log.d("loadUserList", userName)
        this.userListPresenter!!.getUserList(userName)
    }

    //리사이클러 뷰 셋팅
    fun setupRecyclerView() {
        Log.d("setupRecyclerView", "start")
        val dividerItemDecoration =
            DividerItemDecoration(requireActivity(), LinearLayoutManager(requireActivity()).orientation)
        recyclerView = binding!!.rvUsers
        recyclerView!!.addItemDecoration(dividerItemDecoration)
        recyclerView!!.setHasFixedSize(true)
        usersAdapter = UsersAdapter(usersList)
        usersAdapter!!.setOnItemClickListener(object : UserClickListener {
            override fun setOnClick(userModel: UserModel) {
                Log.v("DEBUG900", "userModel :$userModel")
                usersAdapter!!.notifyDataSetChanged()
                if (userModel.checked!!) {
                    Log.d("getChecked", "true")
                    setUserListPresenter!!.insertUserList(userModel)
                    RxEventBus.instance!!.sendBus(userModel)
                } else {
                    Log.d("getChecked", "false")
                    deleteUserListPresenter!!.deleteUser(userModel)
                }
            }
        })
        recyclerView!!.adapter = usersAdapter
    }

    override fun renderUserlist(userModelCollection: List<UserModel>) {
        Log.v("DEBUG900", "renderUserList")
        this.usersAdapter!!.setUserCollection(userModelCollection)
        usersAdapter!!.notifyDataSetChanged()
    }

    override fun context(): Context {
        return context()
    }
}

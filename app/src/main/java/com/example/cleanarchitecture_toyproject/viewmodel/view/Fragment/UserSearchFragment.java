package com.example.cleanarchitecture_toyproject.viewmodel.view.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cleanarchitecture_toyproject.R;
import com.example.cleanarchitecture_toyproject.data.executor.JobExecutor;
import com.example.cleanarchitecture_toyproject.data.repository.UserRepositoryImpl;
import com.example.cleanarchitecture_toyproject.databinding.FragmentUserSearchBinding;
import com.example.cleanarchitecture_toyproject.domain.executor.UIThread;
import com.example.cleanarchitecture_toyproject.domain.usecase.DeleteUserListUseCase;
import com.example.cleanarchitecture_toyproject.domain.usecase.GetUserListUseCase;
import com.example.cleanarchitecture_toyproject.domain.usecase.SetUserListUseCase;
import com.example.cleanarchitecture_toyproject.provider.DatabaseProvider;
import com.example.cleanarchitecture_toyproject.viewmodel.mapper.UserModelMapper;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
import com.example.cleanarchitecture_toyproject.viewmodel.presenter.UserListPresenter;
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserClickListener;
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserListView;
import com.example.cleanarchitecture_toyproject.viewmodel.view.adapter.UsersAdapter;
import java.util.ArrayList;
import java.util.List;

//user를 검색하는 fragment이다
public class UserSearchFragment extends Fragment implements UserListView{

    private FragmentUserSearchBinding binding;

    //사용자 이름 검색 후 list를 불러오는 presenter
    private UserListPresenter userListPresenter;

    //즐겨찾기 유저 저장하기 presenter
    private UserListPresenter setUserListPresenter;

    //즐겨찾기 유저 삭제 presenter
    private UserListPresenter deleteUserListPresenter;

    public UserSearchFragment() {
        setRetainInstance(true);
    }

    //editText에서 입력 받은 사용자 이름
    private String userName="";

    //recyclerview
    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;

    private ArrayList<UserModel> usersList = new ArrayList<>();

    //provider
    public DatabaseProvider databaseProvider;

    //onActivityCreated에서 databinding 생성하기
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //fragment setting
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_search, container, false);

        Log.d("onCreateView","onCreateView");
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("onActivityCreated","onActivityCreated");
        userListPresenter = new UserListPresenter(new GetUserListUseCase(UserRepositoryImpl.getInstance(), new JobExecutor(), new UIThread()),new UserModelMapper());
        setUserListPresenter = new UserListPresenter(new SetUserListUseCase(UserRepositoryImpl.getInstance(), new JobExecutor(),new UIThread()),new UserModelMapper());
        deleteUserListPresenter = new UserListPresenter(new DeleteUserListUseCase(UserRepositoryImpl.getInstance(), new JobExecutor(), new UIThread()),new UserModelMapper());
        userListPresenter.setView(this);

        //getActivity --> requireActivity()
        databaseProvider = new DatabaseProvider(requireActivity());

        //리사이클러뷰 셋팅
        setupRecyclerView();

        binding.userSearchBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = binding.userSearchEdt.getText().toString();
                Log.d("userName", userName);
                loadUserList(userName);
            }
        });
    }

    public void favoriteDataSync() {
        Log.d("fragementStart","start");
        loadUserList(userName);
    }

    //userlist 정보 끌어오기
    public void loadUserList(String userName){
        Log.d("loadUserList", userName);
        this.userListPresenter.getUserList(userName);
    }

    //리사이클러 뷰 셋팅
    public void setupRecyclerView() {
        Log.d("setupRecyclerView","start");
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), new LinearLayoutManager(getActivity()).getOrientation());
        recyclerView = binding.rvUsers;
        recyclerView.addItemDecoration(dividerItemDecoration);
        //getActivity 다음에 나오는 3이 뭐지?
        recyclerView.setHasFixedSize(true);
        usersAdapter = new UsersAdapter(usersList);
        usersAdapter.setOnItemClickListener(new UserClickListener() {
            @Override
            public void setOnClick(UserModel userModel) {
                Log.v("DEBUG900","userModel :"+userModel);
                usersAdapter.notifyDataSetChanged();
                if(userModel.getChecked()) {
                    Log.d("getChecked", "true");
                    setUserListPresenter.insertUserList(userModel);
                } else {
                    Log.d("getChecked", "false");
                    deleteUserListPresenter.deleteUser(userModel);
                }
            }

        });
        recyclerView.setAdapter(usersAdapter);
    }

    @Override
    public void renderUserlist(List<UserModel> userModelCollection) {
        Log.v("DEBUG900","renderUserList");
        this.usersAdapter.setUserCollection(userModelCollection);
        usersAdapter.notifyDataSetChanged();
    }

    @Override
    public Context context() {
        return context();
    }
}

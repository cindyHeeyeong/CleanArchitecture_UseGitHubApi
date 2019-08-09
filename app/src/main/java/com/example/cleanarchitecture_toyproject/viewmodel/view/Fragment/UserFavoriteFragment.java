package com.example.cleanarchitecture_toyproject.viewmodel.view.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cleanarchitecture_toyproject.R;
import com.example.cleanarchitecture_toyproject.data.executor.JobExecutor;
import com.example.cleanarchitecture_toyproject.data.repository.UserRepositoryImpl;
import com.example.cleanarchitecture_toyproject.databinding.FragmentFavoriteUsersBinding;
import com.example.cleanarchitecture_toyproject.domain.executor.UIThread;
import com.example.cleanarchitecture_toyproject.domain.usecase.DeleteUserListUseCase;
import com.example.cleanarchitecture_toyproject.domain.usecase.SelectUserListUseCase;
import com.example.cleanarchitecture_toyproject.viewmodel.RxBus.RxEventBus;
import com.example.cleanarchitecture_toyproject.viewmodel.mapper.UserModelMapper;
import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
import com.example.cleanarchitecture_toyproject.viewmodel.presenter.FavoriteUserPresenter;
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserClickListener;
import com.example.cleanarchitecture_toyproject.viewmodel.view.UserListView;
import com.example.cleanarchitecture_toyproject.viewmodel.view.adapter.UsersAdapter;
import java.util.ArrayList;
import java.util.List;

//즐겨찾기 유저를 보여주는 화면
public class UserFavoriteFragment extends Fragment implements UserListView {

    private FavoriteUserPresenter favoriteUserPresenter;

    private FavoriteUserPresenter deleteUserPresenter;

    private FragmentFavoriteUsersBinding binding;

    private ArrayList<UserModel> usersList = new ArrayList<>();

    private UsersAdapter usersAdapter;

    private ArrayList<UserModel> userModels = new ArrayList<>();
    //onCreateView에서는
    // 한번 이상 실행될 수 있다.
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_users, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        favoriteUserPresenter = new FavoriteUserPresenter(new SelectUserListUseCase(UserRepositoryImpl.getInstance(), new JobExecutor(), new UIThread()), new UserModelMapper());

        deleteUserPresenter = new FavoriteUserPresenter(new DeleteUserListUseCase(UserRepositoryImpl.getInstance(), new JobExecutor(), new UIThread()), new UserModelMapper());

        favoriteUserPresenter.setView(this);

        //TODO

        RxEventBus.getInstance().getBus().map(object -> (UserModel)object)
                .subscribe(userModel -> {
                   if(userModel instanceof UserModel) {

                       Log.d("rxeventbus2222", String.valueOf(userModel.getAvatar_url()));

                       userModels.add(userModel);

                       setupRecyclerView();
                   }
                });

        //favoriteUserPresenter.SelectFavoriteUser();

        //리사이클러뷰 셋팅
        //setupRecyclerView();
    }


    private void setupRecyclerView() {
        Log.d("userModels",String.valueOf(userModels));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), new LinearLayoutManager(requireActivity()).getOrientation());
        //recyclerview setting
        RecyclerView recyclerView = binding.rvUsers;
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setHasFixedSize(true);
        //usersAdapter = new UsersAdapter(usersList);
        usersAdapter = new UsersAdapter(userModels);
        usersAdapter.setOnItemClickListener(new UserClickListener() {
            @Override
            public void setOnClick(UserModel userModel) {
                Log.v("DEBUG999", "userModel :" + userModel);
                usersAdapter.notifyDataSetChanged();

                //delete user data
                deleteUserPresenter.deleteUserData(userModel);
            }
        });
        recyclerView.setAdapter(usersAdapter);
    }

    @Override
    public void renderUserlist(List<UserModel> userModelCollection) {
        if (userModelCollection != null) {
            usersAdapter.setUserCollection(userModelCollection);
    }
        usersAdapter.notifyDataSetChanged();
    }

    @Override
    public Context context() {
        return context();
    }
}

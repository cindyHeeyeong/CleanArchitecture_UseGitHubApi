package com.example.cleanarchitecture_toyproject.viewmodel.view;

import com.example.cleanarchitecture_toyproject.viewmodel.model.UserModel;
import java.util.List;

public interface UserListView extends LoadDataView {
    void renderUserlist(List<UserModel> userModelCollection);

}

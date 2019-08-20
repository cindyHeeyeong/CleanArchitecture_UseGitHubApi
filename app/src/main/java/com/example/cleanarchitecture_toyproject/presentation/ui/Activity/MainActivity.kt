package com.example.cleanarchitecture_toyproject.presentation.ui.Activity

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cleanarchitecture_toyproject.R
import com.example.cleanarchitecture_toyproject.databinding.ActivityMainBinding
import com.example.cleanarchitecture_toyproject.presentation.ui.Fragment.UserFavoriteFragment
import com.example.cleanarchitecture_toyproject.presentation.ui.Fragment.UserSearchFragment

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    //fragment
    var fragmentManager = supportFragmentManager
    var userSearchFragment = UserSearchFragment()
    var userFavoriteFragment = UserFavoriteFragment()

    var active: Fragment = userSearchFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //androidX Version 부터는 binding.setActivity(this) 안해줘도 되는듯
        fragmentManager.beginTransaction().add(R.id.fragmentcontainer, userSearchFragment, "1").commit()
        fragmentManager.beginTransaction().add(R.id.fragmentcontainer, userFavoriteFragment, "2")
            .hide(userFavoriteFragment).commit()

        //bottomnavigationview click event
        //첫번째 탭 - userList 검색 창, 버튼 recyclerview, 두번째 탭 - 즐겨찾기 recyclerview
        binding.navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    Log.d("navigation", "home")
                    fragmentManager.beginTransaction().hide(active).show(userSearchFragment).commit()
                    active = userSearchFragment
                    //(active as UserSearchFragment).favoriteDataSync()
                    (active as UserSearchFragment).loadTest()
                    true
                }

                R.id.navigation_dashboard -> {
                    Log.d("navigation", "dashboard")
                    fragmentManager.beginTransaction().hide(active).show(userFavoriteFragment).commit()
                    active = userFavoriteFragment
                    true
                }
            }
            false
        }
    }
}

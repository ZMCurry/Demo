package com.example.wan_android.ui.view

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import com.example.wan_android.R
import com.example.wan_android.base.BaseActivity
import com.example.wan_android.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.frag_nav_bottom_navigation) as NavHostFragment
    }

    private val navController: NavController by lazy {
        navHostFragment.navController
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        mBinding.toolbar.title = item.title
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) mBinding.drawerLayout.closeDrawer(GravityCompat.START)
        onNavDestinationSelected(item, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.frag_nav_bottom_navigation).navigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        NavigationUI.setupWithNavController(navigation, navController)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        mBinding.toolbar.run {
            mBinding.toolbar.title = getString(R.string.title_home)
            setSupportActionBar(this)
        }

        mBinding.drawerLayout.run {
            val toggle = ActionBarDrawerToggle(
                this@MainActivity,
                this,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            addDrawerListener(toggle)
            toggle.syncState()
        }


    }
}

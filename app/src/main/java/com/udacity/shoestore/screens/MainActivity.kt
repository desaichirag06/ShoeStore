package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.viewmodel.ShoeViewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val viewModel: ShoeViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        appBarConfiguration = AppBarConfiguration
            .Builder(R.id.shoesListFragment, R.id.loginFragment)
            .build()
        viewModel.getLoginFromPreferences(this)
        viewModel.isAlreadyLoggedIn.observe(this, { isLoggedIn ->
            if (isLoggedIn)
                Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.actionLoginShoesListFragment)
        })

        viewModel.isLogout.observe(this, { isLoggedOut ->
            if (isLoggedOut) {
                navigateToLoginFragment()
                viewModel.eventLogoutComplete()
            }
        })

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null as DrawerLayout?)
    }

    override fun onBackPressed() {
        if (appBarConfiguration.topLevelDestinations.contains(navController.currentDestination?.id))
            finish()
        else
            onSupportNavigateUp()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actionLogOut) {
            viewModel.logout(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToLoginFragment() {
        navController.navigate(R.id.loginFragment)
    }

}

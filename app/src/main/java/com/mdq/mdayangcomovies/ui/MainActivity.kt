package com.mdq.mdayangcomovies.ui

import android.app.SearchManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mdq.mdayangcomovies.R
import com.mdq.mdayangcomovies.databinding.ActivityMainBinding
import com.mdq.mdayangcomovies.ui.omdb.OMDBViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    MenuItem.OnMenuItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private var searchView: SearchView? = null

    val viewModel: OMDBViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_movie, R.id.navigation_series, R.id.navigation_episodes
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        configureSearchView(menu)
        return true
    }

    private fun configureSearchView(menu: Menu?) {
        searchView = MenuItemCompat.getActionView(menu?.findItem(R.id.action_search)) as SearchView
        searchView?.setOnQueryTextListener(this)
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
    }

    private fun openSearchFragment(query: String?) {
        viewModel.getSearch(query)
        binding.navView.visibility = View.GONE
        navController.navigate(R.id.navigation_search)
    }

    private fun closeSearchFragment() {
        viewModel.getSearch(null)
        binding.navView.visibility = View.VISIBLE
        if (navController.currentDestination?.id == R.id.navigation_search)
            navController.popBackStack()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        openSearchFragment(query)
        searchView?.clearFocus()
        return true

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText.isNullOrEmpty()){
            closeSearchFragment()
        }
        return false
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {

        return true
    }
}
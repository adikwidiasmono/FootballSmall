package com.small.main.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import com.small.main.R
import com.small.main.ui.favoritematch.FavoriteMatchFragment
import com.small.main.ui.previousmatch.PrevMatchFragment
import com.small.main.ui.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var fragmentState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Football Match Schedule"
        setSupportActionBar(toolbar)

        bnv_football_match.setOnNavigationItemSelectedListener { item ->
            fragmentState = item.itemId
            when (item.itemId) {
                R.id.it_matches -> {
                    loadFragment(savedInstanceState, PrevMatchFragment(), getString(R.string.matches))
                }
                R.id.it_teams -> {
                    loadFragment(savedInstanceState, TeamsFragment(), getString(R.string.teams))
                }
                R.id.it_favorites -> {
                    loadFragment(savedInstanceState, FavoriteMatchFragment(), getString(R.string.favorites))
                }
            }
            true
        }
        bnv_football_match.selectedItemId = R.id.it_matches
    }

    private fun loadFragment(savedInstanceState: Bundle?, activeFragement: Fragment, activeTitle: String?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fl_football_match, activeFragement, activeTitle)
                    .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        val searchItem = menu?.findItem(R.id.it_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        var searchView: SearchView? = null

        searchItem.let {
            searchView = it?.actionView as SearchView
        }
        searchView.let {
            it?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            it?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(query: String): Boolean {
                    Log.e("onQueryTextChange", "->" + query)
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    Log.e("onQueryTextChange", "->" + query)
                    return false
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }

}

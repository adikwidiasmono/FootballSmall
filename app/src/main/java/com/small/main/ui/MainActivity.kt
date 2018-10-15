package com.small.main.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.small.main.R
import com.small.main.ui.favoritematch.FavoriteMatchFragment
import com.small.main.ui.nextmatch.NextMatchMatchFragment
import com.small.main.ui.previousmatch.PrevMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Football Match Schedule"
        setSupportActionBar(toolbar)

        bnv_football_match.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.it_prev_match -> {
                    loadFragment(savedInstanceState, PrevMatchFragment(), getString(R.string.prev_match))
                }
                R.id.it_next_match -> {
                    loadFragment(savedInstanceState, NextMatchMatchFragment(), getString(R.string.next_match))
                }
                R.id.it_favorites -> {
                    loadFragment(savedInstanceState, FavoriteMatchFragment(), getString(R.string.favorites))
                }
            }
            true
        }
        bnv_football_match.selectedItemId = R.id.it_prev_match
    }

    private fun loadFragment(savedInstanceState: Bundle?, activeFragement: Fragment, activeTitle: String?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fl_football_match, activeFragement, activeTitle)
                    .commit()
        }
    }

}

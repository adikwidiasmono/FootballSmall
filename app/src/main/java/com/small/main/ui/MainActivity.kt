package com.small.main.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.small.main.R
import com.small.main.ui.favorites.FavoritesFragment
import com.small.main.ui.favorites.favoritematch.FavoriteMatchFragment
import com.small.main.ui.matches.MatchesFragment
import com.small.main.ui.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var fragmentState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnv_football_match.setOnNavigationItemSelectedListener { item ->
            fragmentState = item.itemId
            when (item.itemId) {
                R.id.it_matches -> {
                    loadFragment(savedInstanceState, MatchesFragment(), getString(R.string.matches))
                }
                R.id.it_teams -> {
                    loadFragment(savedInstanceState, TeamsFragment(), getString(R.string.teams))
                }
                R.id.it_favorites -> {
                    loadFragment(savedInstanceState, FavoritesFragment(), getString(R.string.favorites))
                }
            }
            true
        }
        bnv_football_match.selectedItemId = R.id.it_matches

        permissionCheck()
    }

    private fun loadFragment(savedInstanceState: Bundle?, activeFragement: Fragment, activeTitle: String?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fl_football_match, activeFragement, activeTitle)
                    .commit()
        }
    }

    private val MY_PERMISSIONS_REQUEST_CALENDAR = 99
    private fun permissionCheck() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR),
                    MY_PERMISSIONS_REQUEST_CALENDAR)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CALENDAR -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                                grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    ActivityCompat.requestPermissions(this,
                            arrayOf(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR),
                            MY_PERMISSIONS_REQUEST_CALENDAR)
                }
                return
            }
        }
    }

}

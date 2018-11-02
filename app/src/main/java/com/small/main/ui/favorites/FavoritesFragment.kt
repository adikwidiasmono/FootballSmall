package com.small.main.ui.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.small.main.R
import com.small.main.ui.favorites.favoritematch.FavoriteMatchFragment
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_favorites.view.*

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? = inflater.inflate(R.layout.fragment_favorites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        view.toolbar_favorites.title = "Football Favorites"
        val act = activity as AppCompatActivity
        act.setSupportActionBar(toolbar_favorites)

        setupViewPager(view.vp_favorites)
        view.tl_favorites.setupWithViewPager(view.vp_favorites)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        // TODO : Change to favorite teams
        adapter.addFragment(FavoriteMatchFragment(), getString(R.string.teams))
        adapter.addFragment(FavoriteMatchFragment(), getString(R.string.matches))
        viewPager.adapter = adapter
    }

    class ViewPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
        private val listFragment: MutableList<Fragment> = mutableListOf()
        private val listFragmentTitle: MutableList<String> = mutableListOf()

        override fun getItem(position: Int): Fragment {
            return listFragment.get(position)
        }

        override fun getCount(): Int {
            return listFragment.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return listFragmentTitle.get(position)
        }

        fun addFragment(fragment: Fragment, title: String) {
            listFragment.add(fragment)
            listFragmentTitle.add(title)
        }
    }

}
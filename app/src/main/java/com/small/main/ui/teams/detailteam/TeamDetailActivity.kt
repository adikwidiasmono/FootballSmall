package com.small.main.ui.teams.detailteam

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.small.main.R
import com.small.main.data.local.entity.TeamEntity
import com.small.main.ui.teams.detailteam.overview.OverviewFragment
import com.small.main.ui.teams.detailteam.players.PlayersFragment
import com.small.main.util.CommonUtils
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.design.snackbar
import org.koin.android.ext.android.get

class TeamDetailActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var presenter: TeamDetailPresenter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var matchItem: TeamEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        presenter = get()
        presenter.attachView(this)

        toolbar_team_detail.title = ""
        setSupportActionBar(toolbar_team_detail)
        toolbar_team_detail.setNavigationIcon(R.drawable.ic_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        app_bar_team_detail.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true
                    showOption(R.id.it_search)
                } else if (isShow) {
                    isShow = false
                    hideOption(R.id.it_search)
                }
            }
        })

        matchItem = intent.getSerializableExtra("TEAM_RESULT") as TeamEntity
        matchItem?.let {
            setupViewPager(vp_team_detail)
            tl_team_detail.setupWithViewPager(vp_team_detail)

            Glide.with(this)
                    .load(it.strTeamBadge)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.ic_image_def_128dp)
                            .error(R.drawable.ic_image_err_128dp)
                    )
                    .into(iv_team_icon)

            tv_team_name.text = CommonUtils.safePresentString(it.strTeam)
            tv_team_year_established.text = CommonUtils.safePresentString(it.intFormedYear.toString())
            tv_team_stadium.text = CommonUtils.safePresentString(it.strStadium)

            presenter.checkFavoriteState(it)
        }
    }

    private fun hideOption(id: Int) {
        val item = menuItem?.findItem(id)
        item?.setVisible(false)
    }

    private fun showOption(id: Int) {
        val item = menuItem?.findItem(id)
        item?.setVisible(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.it_add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    override fun onSuccessGetFavoriteState(isFavorite: Boolean) {
        this.isFavorite = isFavorite
        setFavorite()
    }

    override fun onErrorGetFavoriteState(e: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.e("ERR GET FAV STATE", "=>" + e.localizedMessage)
    }

    private fun addToFavorite() {
        matchItem?.let {
            presenter.addTeamToFavorite(it)
        }
    }

    override fun onSuccessAddFavorite(id: Long) {
        snackbar(rl_team_detail, "Added to favorite").show()
    }

    override fun onErrorAddFavorite(e: Throwable) {
        snackbar(rl_team_detail, "Failed add to favorite").show()
    }

    private fun removeFromFavorite() {
        matchItem?.let {
            presenter.removeTeamFromFavorite(it)
        }
    }

    override fun onSuccessRemoveFavorite(deleted: Int) {
        snackbar(rl_team_detail, "Removed from favorite").show()
    }

    override fun onErrorRemoveFavorite(e: Throwable) {
        snackbar(rl_team_detail, "Failed remove from favorite").show()
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(OverviewFragment(), getString(R.string.overview), bundleOf(("TEAM_RESULT" to matchItem)))
        adapter.addFragment(PlayersFragment(), getString(R.string.players), bundleOf(("TEAM_RESULT" to matchItem)))
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

        fun addFragment(fragment: Fragment, title: String, bundle: Bundle) {
            fragment.arguments = bundle
            listFragment.add(fragment)
            listFragmentTitle.add(title)
        }

    }

}

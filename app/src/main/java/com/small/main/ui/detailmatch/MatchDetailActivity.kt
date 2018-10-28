package com.haikotlin.main.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.small.main.R
import com.small.main.data.local.entity.MatchEntity
import com.small.main.ui.detailmatch.MatchDetailPresenter
import com.small.main.ui.detailmatch.MatchDetailView
import com.small.main.util.CommonUtils
import kotlinx.android.synthetic.main.activity_prev_match_detail.*
import org.jetbrains.anko.design.snackbar
import org.koin.android.ext.android.get
import java.text.SimpleDateFormat
import java.util.*

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {

    private lateinit var presenter: MatchDetailPresenter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var matchItem: MatchEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prev_match_detail)

        presenter = get()
        presenter.attachView(this)

        toolbar.title = "Match Detail"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        matchItem = intent.getSerializableExtra("MATCH_RESULT") as MatchEntity
        matchItem?.let {
            val matchDate = SimpleDateFormat("dd/MM/yy HH:mm:ss+HH:mm", Locale.ENGLISH)
                    .parse(it.strDate + " " + it.strTime)

            val matchDateFormatted = SimpleDateFormat("EEE, dd MMM yyyy", Locale("in", "ID"))
            matchDateFormatted.timeZone = TimeZone.getTimeZone("Asia/Jakarta");

            tv_match_date.text = matchDateFormatted.format(matchDate)

            val strs = it.strEvent?.split("vs")
            var homeTeam = strs?.get(0)?.trim() ?: "Unknown Team"
            var awayTeam = strs?.get(1)?.trim() ?: "Unknown Team"

            if (homeTeam.length > 14)
                homeTeam.substring(0, 11) + "..."
            if (awayTeam.length > 14)
                awayTeam.substring(0, 11) + "..."

            tv_home_team_name.text = CommonUtils.safePresentString(homeTeam)
            tv_away_team_name.text = CommonUtils.safePresentString(awayTeam)

            tv_home_team_score.text = CommonUtils.safePresentString(it.intHomeScore.toString())
            tv_away_team_score.text = CommonUtils.safePresentString(it.intAwayScore.toString())

            tv_home_formation.text = CommonUtils.safePresentString(it.strHomeFormation)
            tv_away_formation.text = CommonUtils.safePresentString(it.strAwayFormation)

            tv_home_goal_players.text = CommonUtils.safePresentString(it.strHomeGoalDetails?.replace(";", "\n"))
            tv_away_goal_players.text = CommonUtils.safePresentString(it.strAwayGoalDetails?.replace(";", "\n"))

            tv_home_shots.text = CommonUtils.safePresentString(it.intHomeShots.toString())
            tv_away_shots.text = CommonUtils.safePresentString(it.intAwayShots.toString())

            tv_home_goalkeeper.text = CommonUtils.safePresentString(it.strHomeLineupGoalkeeper)
            tv_away_goalkeeper.text = CommonUtils.safePresentString(it.strAwayLineupGoalkeeper)

            tv_home_defense.text = CommonUtils.safePresentString(it.strHomeLineupDefense)
            tv_away_defense.text = CommonUtils.safePresentString(it.strAwayLineupDefense)

            tv_home_midfiled.text = CommonUtils.safePresentString(it.strHomeLineupMidfield)
            tv_away_midfield.text = CommonUtils.safePresentString(it.strAwayLineupMidfield)

            tv_home_forward.text = CommonUtils.safePresentString(it.strHomeLineupForward)
            tv_away_forward.text = CommonUtils.safePresentString(it.strAwayLineupForward)

            tv_home_subtitutes.text = CommonUtils.safePresentString(it.strHomeLineupSubstitutes)
            tv_away_subtitutes.text = CommonUtils.safePresentString(it.strAwayLineupSubstitutes)

            it.idHomeTeam?.let { presenter.setTeamLogo(this, it, iv_home_logo) }
            it.idAwayTeam?.let { presenter.setTeamLogo(this, it, iv_away_logo) }

            presenter.checkFavoriteState(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
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
            presenter.addMatchToFavorite(it)
        }
    }

    override fun onSuccessAddFavorite(id: Long) {
        snackbar(ll_match_detail, "Added to favorite").show()
    }

    override fun onErrorAddFavorite(e: Throwable) {
        snackbar(ll_match_detail, "Failed add to favorite").show()
    }

    private fun removeFromFavorite() {
        matchItem?.let {
            presenter.removeMatchToFavorite(it)
        }
    }

    override fun onSuccessRemoveFavorite(deleted: Int) {
        snackbar(ll_match_detail, "Removed from favorite").show()
    }

    override fun onErrorRemoveFavorite(e: Throwable) {
        snackbar(ll_match_detail, "Failed remove from favorite").show()
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

}

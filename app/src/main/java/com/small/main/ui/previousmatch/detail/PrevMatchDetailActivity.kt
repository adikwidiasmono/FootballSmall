package com.haikotlin.main.detail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.small.main.R
import com.small.main.data.local.entity.MatchEntity
import com.small.main.ui.previousmatch.detail.PrevMatchDetailPresenter
import com.small.main.ui.previousmatch.detail.PrevMatchDetailView
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_prev_match_detail.*
import org.jetbrains.anko.db.SelectQueryBuilder
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.design.snackbar
import org.koin.android.ext.android.get
import java.text.SimpleDateFormat
import java.util.*

class PrevMatchDetailActivity : AppCompatActivity(), PrevMatchDetailView {

    private lateinit var prevMatchDetailPresenter: PrevMatchDetailPresenter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var matchItem: MatchEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prev_match_detail)

        prevMatchDetailPresenter = get()
        prevMatchDetailPresenter.attachView(this)

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

            tv_home_team_name.text = homeTeam
            tv_away_team_name.text = awayTeam

            tv_home_team_score.text = it.intHomeScore.toString()
            tv_away_team_score.text = it.intAwayScore.toString()

            tv_home_formation.text = it.strHomeFormation ?: "-"
            tv_away_formation.text = it.strAwayFormation ?: "-"

            tv_home_goal_players.text = it.strHomeGoalDetails?.replace(";", "\n") ?: "-"
            tv_away_goal_players.text = it.strAwayGoalDetails?.replace(";", "\n") ?: "-"

            tv_home_shots.text = it.intHomeShots.toString()
            tv_away_shots.text = it.intAwayShots.toString()

            tv_home_goalkeeper.text = it.strHomeLineupGoalkeeper ?: "-"
            tv_away_goalkeeper.text = it.strAwayLineupGoalkeeper ?: "-"

            tv_home_defense.text = it.strHomeLineupDefense ?: "-"
            tv_away_defense.text = it.strAwayLineupDefense ?: "-"

            tv_home_midfiled.text = it.strHomeLineupMidfield ?: "-"
            tv_away_midfield.text = it.strAwayLineupMidfield ?: "-"

            tv_home_forward.text = it.strHomeLineupForward ?: "-"
            tv_away_forward.text = it.strAwayLineupForward ?: "-"

            tv_home_subtitutes.text = it.strHomeLineupSubstitutes ?: "-"
            tv_away_subtitutes.text = it.strAwayLineupSubstitutes ?: "-"

            it.idHomeTeam?.let { getHomeLogo(it.toString()) }
            it.idAwayTeam?.let { getAwayLogo(it.toString()) }

            prevMatchDetailPresenter.checkFavoriteState(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        prevMatchDetailPresenter.detachView()
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
    }

    private fun addToFavorite() {
        matchItem?.let {
            prevMatchDetailPresenter.addMatchToFavorite(it)
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
            prevMatchDetailPresenter.removeMatchToFavorite(it)
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

    private fun getHomeLogo(idTeam: String) {
        // Get data here
        disposableHome =
                footballApiService.lookupTeam(idTeam)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    run {
                                        Glide.with(this)
                                                .load(result?.teams?.get(0)?.strTeamBadge)
                                                .apply(RequestOptions()
                                                        .placeholder(R.drawable.ic_image_def_128dp)
                                                        .error(R.drawable.ic_image_err_128dp)
                                                )
                                                .into(iv_home_logo)
                                    }
                                },
                                { error ->
                                    run {
                                        Glide.with(this)
                                                .load(R.drawable.ic_image_err_128dp)
                                                .into(iv_home_logo)
                                        Log.e("FAILED IMG", "Home clud logo : " + error.localizedMessage)
                                    }
                                }
                        )
    }

    private fun getAwayLogo(idTeam: String) {
        // Get data here
        disposableAway =
                footballApiService.lookupTeam(idTeam)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result ->
                                    run {
                                        Glide.with(this)
                                                .load(result?.teams?.get(0)?.strTeamBadge)
                                                .apply(RequestOptions()
                                                        .placeholder(R.drawable.ic_image_def_128dp)
                                                        .error(R.drawable.ic_image_err_128dp)
                                                )
                                                .into(iv_away_logo)
                                    }
                                },
                                { error ->
                                    run {
                                        Glide.with(this)
                                                .load(R.drawable.ic_image_err_128dp)
                                                .into(iv_away_logo)
                                        Log.e("FAILED IMG", "Away clud logo : " + error.localizedMessage)
                                    }
                                }
                        )
    }

}

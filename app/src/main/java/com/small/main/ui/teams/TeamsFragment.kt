package com.small.main.ui.teams

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.small.main.R
import com.small.main.data.remote.response.FootballTeamItem
import com.small.main.data.remote.response.LeagueListResponse
import com.small.main.data.remote.response.MatchResponse
import com.small.main.data.remote.response.TeamListResponse
import com.small.main.ui.adapter.recycleview.EventAdapter
import com.small.main.ui.adapter.recycleview.TeamAdapter
import com.small.main.ui.adapter.spinner.LeagueAdapter
import com.small.main.util.gone
import com.small.main.util.visible
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.support.v4.onRefresh
import org.koin.android.ext.android.get


class TeamsFragment : Fragment(), TeamsView {

    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamAdapter
    private val listData: MutableList<FootballTeamItem> = mutableListOf()

    private lateinit var spAdapter: LeagueAdapter

    // Default league ID
    private var idLeague = 4328

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_teams, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        presenter = get()
        presenter.attachView(this)
        // Load teams based on initial league ID
//        presenter.loadTeams(idLeague)
        // Load all league
        presenter.loadLeagues()

        srl_teams.onRefresh {
            presenter.loadTeams(idLeague, false)
        }
    }

    private fun initList() {
        adapter = TeamAdapter(listData) {
            //            startActivity<MatchDetailActivity>(
//                    "MATCH_RESULT" to ParseUtils.matchResponseToEntity(it)
//            )
        }
        rv_teams.layoutManager = LinearLayoutManager(activity)
        rv_teams.adapter = this.adapter


    }

    override fun showLeagueList(leagueListResponse: LeagueListResponse) {
        val sortedList = leagueListResponse.countrys?.sortedWith(
                compareBy({ it?.strLeague })
        )
        spAdapter = LeagueAdapter(activity, R.layout.item_league, sortedList)
        sp_teams.adapter = spAdapter

        sp_teams?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                idLeague = spAdapter.getItem(position)?.idLeague ?: 4328
                presenter.loadTeams(idLeague)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    override fun onErrorLeagueList() {
        sp_teams.gone()
        // Load teams based on default league ID
        presenter.loadTeams(idLeague)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showLoading() {
        pb_teams.visible()
    }

    override fun hideLoading() {
        pb_teams.gone()
        srl_teams.isRefreshing = false
    }

    override fun showTeamList(teamListResponse: TeamListResponse) {
        listData.clear()
        teamListResponse.teams?.let {
            for (i in teamListResponse.teams.indices) {
                listData.add(teamListResponse.teams[i])
            }
        }

        adapter.notifyDataSetChanged()

        srl_teams.isRefreshing = false
    }

    override fun onErrorTeamList() {
        val snbar = Snackbar.make(
                rl_teams,
                getString(R.string.err_get_remote_data),
                Snackbar.LENGTH_LONG
        );
        snbar.setAction("RELOAD",
                {
                    presenter.loadTeams(idLeague)
                    snbar.dismiss()
                }
        )
        snbar.show()
    }

}
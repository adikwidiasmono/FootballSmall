package com.small.main.ui.matches.nextmatch

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.haikotlin.main.detail.MatchDetailActivity
import com.small.main.R
import com.small.main.data.remote.response.LeagueListResponse
import com.small.main.data.remote.response.MatchByLeagueListResponse
import com.small.main.data.remote.response.MatchResponse
import com.small.main.ui.adapter.recycleview.EventAdapter
import com.small.main.ui.adapter.spinner.LeagueAdapter
import com.small.main.util.*
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.ext.android.get

class NextMatchFragment : Fragment(), NextMatchView {

    private lateinit var presenter: NextMatchPresenter
    private lateinit var adapter: EventAdapter
    private val listData: MutableList<MatchResponse> = mutableListOf()

    private lateinit var spAdapter: LeagueAdapter

    // Default league ID
    private var idLeague = 4328

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_next_match, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        presenter = get()
        presenter.attachView(this)
        // Load all league
        presenter.loadLeagues()

        srl_next_match.onRefresh {
            presenter.loadNextMatch(idLeague, false)
        }
    }

    private fun initList() {
        adapter = EventAdapter(listData) {
            startActivity<MatchDetailActivity>(
                    "MATCH_RESULT" to ParseUtils.matchResponseToEntity(it)
            )
        }
        rv_next_match.layoutManager = LinearLayoutManager(activity)
        rv_next_match.adapter = this.adapter
    }

    override fun showLeagueList(leagueListResponse: LeagueListResponse) {
        val sortedList = leagueListResponse.countrys?.sortedWith(
                compareBy({ it?.strLeague })
        )
        spAdapter = LeagueAdapter(activity, R.layout.item_league, sortedList)
        sp_next_match.adapter = spAdapter

        // Set default selection to Premier League
        sp_next_match.setSelection(17)

        sp_next_match?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                idLeague = spAdapter.getItem(position)?.idLeague ?: 4328
                presenter.loadNextMatch(idLeague)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    override fun onErrorLeagueList() {
        sp_next_match.gone()
        // Load next match based on default league ID
        presenter.loadNextMatch(idLeague)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showLoading() {
        pb_next_match.visible()
    }

    override fun hideLoading() {
        pb_next_match.gone()
        srl_next_match.isRefreshing = false
    }

    override fun showMatchList(matchByLeagueListResponse: MatchByLeagueListResponse) {
        listData.clear()
        matchByLeagueListResponse.events?.let {
            for (i in matchByLeagueListResponse.events.indices) {
                listData.add(matchByLeagueListResponse.events[i])
            }
        }
        adapter.notifyDataSetChanged()

        srl_next_match.isRefreshing = false
    }

    override fun showErrorMatchList() {
        val snbar = Snackbar.make(
                rl_next_match,
                getString(R.string.err_get_remote_data),
                Snackbar.LENGTH_LONG
        );
        snbar.setAction("RELOAD",
                {
                    presenter.loadNextMatch(idLeague)
                    snbar.dismiss()
                }
        )
        snbar.show()
    }

}
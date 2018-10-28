package com.small.main.ui.nextmatch

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haikotlin.main.detail.MatchDetailActivity
import com.small.main.R
import com.small.main.data.remote.response.MatchListResponse
import com.small.main.data.remote.response.MatchResponse
import com.small.main.ui.adapter.EventAdapter
import com.small.main.util.*
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.ext.android.get

class NextMatchFragment : Fragment(), NextMatchView {

    private lateinit var presenter: NextMatchPresenter
    private lateinit var adapter: EventAdapter
    private val listData: MutableList<MatchResponse> = mutableListOf()

    private val leagueId = 4328

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_next_match, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        presenter = get()
        presenter.attachView(this)
        presenter.loadNextMatch(leagueId)

        srl_next_match.onRefresh {
            presenter.loadNextMatch(leagueId, false)
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

    override fun showResultList(matchListResponse: MatchListResponse) {
        listData.clear()
        matchListResponse.events?.let {
            for (i in matchListResponse.events.indices) {
                listData.add(matchListResponse.events[i])
            }
        }
        adapter.notifyDataSetChanged()

        srl_next_match.isRefreshing = false
    }

    override fun showError() {
        val snbar = Snackbar.make(
                rl_next_match,
                getString(R.string.err_get_remote_data),
                Snackbar.LENGTH_LONG
        );
        snbar.setAction("RELOAD",
                {
                    presenter.loadNextMatch(leagueId)
                    snbar.dismiss()
                }
        )
        snbar.show()
    }

}
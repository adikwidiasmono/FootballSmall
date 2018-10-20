package com.small.main.ui.previousmatch

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haikotlin.main.detail.PrevMatchDetailActivity
import com.small.main.R
import com.small.main.data.remote.response.MatchListResponse
import com.small.main.data.remote.response.MatchResponse
import com.small.main.ui.adapter.EventAdapter
import com.small.main.util.ParseUtils
import com.small.main.util.gone
import com.small.main.util.visible
import kotlinx.android.synthetic.main.fragment_previous_match.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.ext.android.get

class PrevMatchFragment : Fragment(), PrevMatchView {

    private lateinit var presenter: PrevMatchPresenter
    private lateinit var adapter: EventAdapter
    private val listData: MutableList<MatchResponse> = mutableListOf()

    private val leagueId = 4328

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_previous_match, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        presenter = get()
        presenter.attachView(this)
        presenter.loadLastMatch(leagueId)

        srl_previous_match.onRefresh {
            presenter.loadLastMatch(leagueId, false)
        }
    }

    private fun initList() {
        adapter = EventAdapter(listData) {
            startActivity<PrevMatchDetailActivity>(
                    "MATCH_RESULT" to ParseUtils.matchResponseToEntity(it)
            )
        }
        rv_previous_match.layoutManager = LinearLayoutManager(activity)
        rv_previous_match.adapter = this.adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showLoading() {
        pb_previous_match.visible()
    }

    override fun hideLoading() {
        pb_previous_match.gone()
        srl_previous_match.isRefreshing = false
    }

    override fun showResultList(matchListResponse: MatchListResponse) {
        listData.clear()
        matchListResponse.events?.let {
            for (i in matchListResponse.events.indices) {
                listData.add(matchListResponse.events[i])
            }
        }

        adapter.notifyDataSetChanged()

        srl_previous_match.isRefreshing = false
    }

    override fun showError() {
        val snbar = Snackbar.make(
                rl_previous_match,
                getString(R.string.err_get_remote_data),
                Snackbar.LENGTH_LONG
        );
        snbar.setAction("RELOAD",
                {
                    presenter.loadLastMatch(leagueId)
                    snbar.dismiss()
                }
        )
        snbar.show()
    }

}
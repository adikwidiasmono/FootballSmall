package com.small.main.ui.previousmatch

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.small.main.R
import com.small.main.data.response.EventListResponse
import com.small.main.data.response.EventResponse
import com.small.main.ui.adapter.EventAdapter
import com.small.main.util.gone
import com.small.main.util.visible
import kotlinx.android.synthetic.main.fragment_previous_match.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.ext.android.get

class PrevMatchFragment : Fragment(), PrevMatchView {

    private lateinit var prevMatchPresenter: PrevMatchPresenter
    private lateinit var adapter: EventAdapter
    private val events: MutableList<EventResponse> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_previous_match, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        prevMatchPresenter = get()
        prevMatchPresenter.attachView(this)
        getData()

        srl_previous_match.onRefresh {
            getData()
        }
    }

    private fun getData() {
        prevMatchPresenter.loadLastMatch(4328)
    }

    private fun initList() {
        adapter = EventAdapter(events) {
            startActivity<MatchDetailActivity>(
                    "MATCH_RESULT" to it
            )
        }
        rv_previous_match.layoutManager = LinearLayoutManager(activity)
        rv_previous_match.adapter = this.adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        prevMatchPresenter.detachView()
    }

    override fun showLoading() {
        pb_previous_match.visible()
    }

    override fun hideLoading() {
        pb_previous_match.gone()
    }

    override fun showResultList(eventListResponse: EventListResponse) {
        events.clear()
        eventListResponse.events?.let {
            for (i in eventListResponse.events.indices) {
                events.add(eventListResponse.events[i])
            }
        }
        adapter.notifyDataSetChanged()

        srl_previous_match.isRefreshing = false
    }

    override fun showError() {
        val snbar = Snackbar.make(
                cl_previous_match,
                getString(R.string.err_get_remote_data),
                Snackbar.LENGTH_INDEFINITE
        );
        snbar.setAction("RELOAD",
                {
                    getData()
                    snbar.dismiss()
                }
        )
        snbar.show()
    }

}
package com.small.main.ui.favoritematch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.small.main.R
import com.small.main.data.response.EventListResponse
import com.small.main.data.response.EventResponse
import com.small.main.ui.adapter.EventAdapter
import com.small.main.util.*
import kotlinx.android.synthetic.main.fragment_today_match.*
import org.koin.android.ext.android.get

class FavoriteMatchFragment : Fragment(), FavoriteMatchView {

    private lateinit var favoriteMatchPresenter: FavoriteMatchPresenter
    private lateinit var adapter: EventAdapter
    private val events: MutableList<EventResponse> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_today_match, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        favoriteMatchPresenter = get()
        favoriteMatchPresenter.attachView(this)
        // TODO change hardcoded date with calendar instance time
        favoriteMatchPresenter.loadTodayMatch(4328, "2018-10-06")
    }

    private fun initList() {
        adapter = EventAdapter(events)
        rv_today_match.layoutManager = LinearLayoutManager(activity)
        rv_today_match.adapter = this.adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        favoriteMatchPresenter.detachView()
    }

    override fun showLoading() {
        pb_today.visible()
    }

    override fun hideLoading() {
        pb_today.gone()
    }

    override fun showResultList(eventListResponse: EventListResponse) {
        events.clear()
        eventListResponse.events?.let {
            for (i in eventListResponse.events.indices) {
                events.add(eventListResponse.events[i])
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun showError() {
        Toast.makeText(context, "Error, failed to get data from server", Toast.LENGTH_SHORT).show()
    }

}